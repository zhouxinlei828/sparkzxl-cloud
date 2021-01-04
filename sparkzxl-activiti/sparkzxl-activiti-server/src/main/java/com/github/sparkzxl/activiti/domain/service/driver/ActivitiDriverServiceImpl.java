package com.github.sparkzxl.activiti.domain.service.driver;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.sparkzxl.activiti.application.service.act.IProcessRepositoryService;
import com.github.sparkzxl.activiti.application.service.act.IProcessRuntimeService;
import com.github.sparkzxl.activiti.application.service.act.IProcessTaskService;
import com.github.sparkzxl.activiti.application.service.driver.IActivitiDriverService;
import com.github.sparkzxl.activiti.application.service.ext.IExtHiTaskStatusService;
import com.github.sparkzxl.activiti.application.service.ext.IExtProcessStatusService;
import com.github.sparkzxl.activiti.domain.model.DriveProcess;
import com.github.sparkzxl.activiti.dto.ActivitiDataDTO;
import com.github.sparkzxl.activiti.dto.DriverProcessDTO;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.dto.UserNextTask;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import com.github.sparkzxl.activiti.infrastructure.convert.ActivitiDriverConvert;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtHiTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessStatus;
import com.github.sparkzxl.activiti.infrastructure.strategy.AbstractActivitiSolver;
import com.github.sparkzxl.activiti.infrastructure.strategy.ActivitiSolverChooser;
import com.github.sparkzxl.activiti.infrastructure.utils.ActivitiUtils;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessNextTaskDTO;
import com.github.sparkzxl.core.utils.DateUtils;
import com.github.sparkzxl.core.utils.ListUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * description: 流程驱动 服务 实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:27:58
 */
@Service
@Slf4j
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
public class ActivitiDriverServiceImpl implements IActivitiDriverService {


    @Autowired
    private ActivitiSolverChooser activitiSolverChooser;

    @Autowired
    private IExtHiTaskStatusService extHiTaskStatusService;

    @Autowired
    private IExtProcessStatusService extProcessStatusService;

    @Autowired
    private IProcessRepositoryService processRepositoryService;

    @Autowired
    private IProcessRuntimeService processRuntimeService;

    @Autowired
    private IProcessTaskService processTaskService;

    @Override
    public DriverResult driverProcess(DriverProcessDTO driverProcessDTO) {
        int actType = driverProcessDTO.getActType();
        AbstractActivitiSolver activitiSolver = activitiSolverChooser.chooser(actType);
        DriveProcess driveProcess = ActivitiDriverConvert.INSTANCE.convertDriveProcess(driverProcessDTO);
        return activitiSolver.slove(driverProcessDTO.getBusinessId(), driveProcess);
    }

    @Override
    public List<UserNextTask> getNextUserTask(ProcessNextTaskDTO processNextTaskDTO) {
        Task currentTask = processTaskService.getLatestTaskByProInstId(processNextTaskDTO.getProcessInstanceId());
        List<UserTask> userTasks = Lists.newArrayList();
        //获取BpmnModel对象
        BpmnModel bpmnModel = processRepositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        //获取Process对象
        Process process = bpmnModel.getProcesses().get(bpmnModel.getProcesses().size() - 1);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        //获取当前节点信息
        FlowElement flowElement = ActivitiUtils.getFlowElementById(currentTask.getTaskDefinitionKey(), flowElements);
        ActivitiUtils.getNextNode(flowElements, flowElement, processNextTaskDTO.getVariables(), userTasks);
        log.info("userTasks = {}", userTasks);
        List<UserNextTask> userNextTasks = Lists.newArrayList();
        if (ListUtils.isNotEmpty(userTasks)) {
            userTasks.forEach(item -> {
                UserNextTask userNextTask = new UserNextTask();
                userNextTask.setAssignee(item.getAssignee());
                userNextTask.setOwner(item.getOwner());
                userNextTask.setPriority(item.getPriority());
                userNextTask.setDueDate(item.getDueDate());
                userNextTask.setBusinessCalendarName(item.getBusinessCalendarName());
                userNextTask.setCandidateUsers(item.getCandidateUsers());
                userNextTask.setCandidateGroups(item.getCandidateGroups());
                userNextTask.setTaskDefKey(item.getId());
                userNextTask.setTaskName(item.getName());
                userNextTasks.add(userNextTask);
            });
        }
        return userNextTasks;
    }

    @Override
    public ActivitiDataDTO findActivitiData(String businessId, String processDefinitionKey) {
        ActivitiDataDTO activitiDataDTO = new ActivitiDataDTO();
        activitiDataDTO.setProcessDefinitionKey(processDefinitionKey);
        activitiDataDTO.setBusinessId(businessId);
        ProcessInstance processInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
        Map<Object, Object> actionMap = Maps.newHashMap();
        if (ObjectUtils.isNotEmpty(processInstance)) {
            actionMap.put(WorkflowConstants.WorkflowAction.SUBMIT, "提交");
            actionMap.put(WorkflowConstants.WorkflowAction.AGREE, "同意");
            actionMap.put(WorkflowConstants.WorkflowAction.JUMP, "跳转");
            actionMap.put(WorkflowConstants.WorkflowAction.ROLLBACK, "驳回");
            Task lastTask = processTaskService.getLatestTaskByProInstId(processInstance.getProcessInstanceId());
            List<IdentityLink> identityLinks = processTaskService.getIdentityLinksForTask(lastTask.getId());
            List<String> candidateGroupList = Lists.newArrayList();
            List<String> assigneeList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(identityLinks)) {
                identityLinks.forEach(identityLink -> {
                    if (StringUtils.isNoneEmpty(identityLink.getGroupId())) {
                        candidateGroupList.add(identityLink.getGroupId());
                    }
                    if (StringUtils.isNoneEmpty(identityLink.getUserId())) {
                        assigneeList.add(identityLink.getUserId());
                    }
                });
            }
            UserNextTask userNextTask = new UserNextTask();
            userNextTask.setAssignee(ListUtils.listToString(assigneeList));
            userNextTask.setOwner(lastTask.getOwner());
            userNextTask.setPriority(String.valueOf(lastTask.getPriority()));
            userNextTask.setDueDate(DateUtils.format(lastTask.getDueDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            userNextTask.setCandidateUsers(assigneeList);
            userNextTask.setCandidateGroups(candidateGroupList);
            userNextTask.setTaskDefKey(lastTask.getTaskDefinitionKey());
            userNextTask.setTaskName(lastTask.getName());
            activitiDataDTO.setUserNextTask(userNextTask);
        } else {
            actionMap.put(WorkflowConstants.WorkflowAction.START, "启动");
        }
        activitiDataDTO.setActTypeMap(actionMap);
        return activitiDataDTO;
    }

    @Override
    public List<ActivitiDataDTO> findActivitiDataList(List<String> businessIds, String processDefinitionKey) {
        List<ActivitiDataDTO> activitiDataDTOList = Lists.newArrayList();
        businessIds.forEach(x -> activitiDataDTOList.add(findActivitiData(x, processDefinitionKey)));
        return activitiDataDTOList;
    }

    @Override
    public boolean suspendProcess(String businessId) {
        return processRuntimeService.suspendProcess(businessId);
    }

    @Override
    public boolean deleteProcessInstance(String businessId, String deleteReason) {
        ProcessInstance processInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
        if (ObjectUtils.isNotEmpty(processInstance)) {
            String processInstanceId = processInstance.getProcessInstanceId();
            processRuntimeService.deleteProcessInstance(processInstanceId, deleteReason);
            extHiTaskStatusService.remove(new LambdaUpdateWrapper<ExtHiTaskStatus>().eq(ExtHiTaskStatus::getProcessInstanceId,
                    processInstanceId));
            extProcessStatusService.remove(new LambdaUpdateWrapper<ExtProcessStatus>().eq(ExtProcessStatus::getProcessInstanceId,
                    processInstanceId));
        }
        return true;
    }

    @Override
    public boolean deleteProcessByProcInsId(String processInstanceId, String deleteReason) {
        ProcessInstance processInstance = processRuntimeService.getProcessInstance(processInstanceId);
        if (ObjectUtils.isNotEmpty(processInstance)) {
            processRuntimeService.deleteProcessInstance(processInstanceId, deleteReason);
            extHiTaskStatusService.remove(new LambdaUpdateWrapper<ExtHiTaskStatus>().eq(ExtHiTaskStatus::getProcessInstanceId,
                    processInstanceId));
            extProcessStatusService.remove(new LambdaUpdateWrapper<ExtProcessStatus>().eq(ExtProcessStatus::getProcessInstanceId,
                    processInstanceId));
        }
        return true;
    }
}
