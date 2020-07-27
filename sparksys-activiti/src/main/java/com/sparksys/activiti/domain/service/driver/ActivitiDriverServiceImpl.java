package com.sparksys.activiti.domain.service.driver;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sparksys.activiti.application.service.driver.IActivitiDriverService;
import com.sparksys.activiti.application.service.act.IProcessRepositoryService;
import com.sparksys.activiti.application.service.process.IProcessTaskStatusService;
import com.sparksys.activiti.domain.entity.DriveProcess;
import com.sparksys.activiti.infrastructure.entity.ProcessInstance;
import com.sparksys.activiti.infrastructure.strategy.AbstractActivitiSolver;
import com.sparksys.activiti.infrastructure.strategy.ActivitiSolverChooser;
import com.sparksys.activiti.infrastructure.utils.ActivitiUtils;
import com.sparksys.activiti.interfaces.dto.act.InstancePageDTO;
import com.sparksys.activiti.interfaces.dto.act.ProcessInstanceDTO;
import com.sparksys.activiti.interfaces.dto.driver.DriveProcessDTO;
import com.sparksys.activiti.interfaces.dto.process.ProcessNextTaskDTO;
import com.sparksys.activiti.interfaces.dto.process.UserNextTask;
import com.sparksys.core.utils.ListUtils;
import com.sparksys.database.utils.PageInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

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
    private IProcessTaskStatusService processTaskStatusService;

    @Autowired
    private IProcessRepositoryService processRepositoryService;

    @Override
    public boolean driveProcess(DriveProcessDTO driveProcessDTO) {
        int actType = driveProcessDTO.getActType();
        AbstractActivitiSolver activitiSolver = activitiSolverChooser.chooser(actType);
        DriveProcess driveProcess = new DriveProcess();
        BeanUtils.copyProperties(driveProcessDTO, driveProcess);
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
            userTasks.forEach(item -> userNextTasks.add(UserNextTask.builder()
                    .assignee(item.getAssignee())
                    .owner(item.getOwner())
                    .priority(item.getPriority())
                    .dueDate(item.getDueDate())
                    .businessCalendarName(item.getBusinessCalendarName())
                    .candidateUsers(item.getCandidateUsers())
                    .candidateGroups(item.getCandidateGroups())
                    .taskName(item.getName())
                    .taskDefKey(item.getId())
                    .build()));
        }
        return userNextTasks;
    }
}
