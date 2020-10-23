package com.github.sparkzxl.activiti.domain.service.driver;

import com.github.sparkzxl.activiti.application.service.act.IProcessRepositoryService;
import com.github.sparkzxl.activiti.application.service.act.IProcessRuntimeService;
import com.github.sparkzxl.activiti.application.service.driver.IActivitiDriverService;
import com.github.sparkzxl.activiti.application.service.process.IActHiTaskStatusService;
import com.github.sparkzxl.activiti.domain.entity.DriveProcess;
import com.github.sparkzxl.activiti.dto.ActivitiDataDTO;
import com.github.sparkzxl.activiti.dto.DriverProcessDTO;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.dto.UserNextTask;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import com.github.sparkzxl.activiti.infrastructure.convert.ActivitiDriverConvert;
import com.github.sparkzxl.activiti.infrastructure.entity.ActHiTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.strategy.AbstractActivitiSolver;
import com.github.sparkzxl.activiti.infrastructure.strategy.ActivitiSolverChooser;
import com.github.sparkzxl.activiti.infrastructure.utils.ActivitiUtils;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessNextTaskDTO;
import com.github.sparkzxl.core.utils.ListUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private IActHiTaskStatusService actHiTaskStatusService;

    @Autowired
    private IProcessRepositoryService processRepositoryService;

    @Autowired
    private IProcessRuntimeService processRuntimeService;

    @Override
    public DriverResult driverProcess(DriverProcessDTO driverProcessDTO) {
        int actType = driverProcessDTO.getActType();
        AbstractActivitiSolver activitiSolver = activitiSolverChooser.chooser(actType);
        DriveProcess driveProcess = ActivitiDriverConvert.INSTANCE.convertDriveProcess(driverProcessDTO);
        return activitiSolver.slove(driveProcess);
    }

    @Override
    public List<UserNextTask> getNextUserTask(ProcessNextTaskDTO processNextTaskDTO) {
        List<UserTask> userTasks = Lists.newArrayList();
        //获取BpmnModel对象
        BpmnModel bpmnModel = processRepositoryService.getBpmnModel(processNextTaskDTO.getProcessDefinitionId());
        //获取Process对象
        Process process = bpmnModel.getProcesses().get(bpmnModel.getProcesses().size() - 1);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        //获取当前节点信息
        FlowElement flowElement = ActivitiUtils.getFlowElementById(processNextTaskDTO.getTaskDefKey(), flowElements);
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
            ProcessNextTaskDTO processNextTaskDTO = new ProcessNextTaskDTO();
            processNextTaskDTO.setProcessDefinitionId(processInstance.getProcessDefinitionId());
            ActHiTaskStatus actHiTaskStatus = actHiTaskStatusService.getActHiTaskStatus(processInstance.getProcessInstanceId());
            processNextTaskDTO.setTaskDefKey(actHiTaskStatus.getTaskDefKey());
            Map<String, Object> variables = Maps.newHashMap();
            variables.put("actType", 1);
            processNextTaskDTO.setVariables(variables);
            List<UserNextTask> userNextTasks = getNextUserTask(processNextTaskDTO);
            if (CollectionUtils.isNotEmpty(userNextTasks)) {
                UserNextTask userNextTask = userNextTasks.get(0);
                activitiDataDTO.setUserNextTask(userNextTask);
            }
        } else {
            actionMap.put(WorkflowConstants.WorkflowAction.START, "启动");
        }
        activitiDataDTO.setActionMap(actionMap);
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
        ProcessInstance processInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
        if (ObjectUtils.isNotEmpty(processInstance)) {
            processRuntimeService.suspendProcess(processInstance.getProcessInstanceId());
            return true;
        }
        return false;
    }
}
