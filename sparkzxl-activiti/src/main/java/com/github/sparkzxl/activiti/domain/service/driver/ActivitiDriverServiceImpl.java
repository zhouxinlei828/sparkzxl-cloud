package com.github.sparkzxl.activiti.domain.service.driver;

import com.google.common.collect.Lists;
import com.github.sparkzxl.activiti.application.service.driver.IActivitiDriverService;
import com.github.sparkzxl.activiti.application.service.act.IProcessRepositoryService;
import com.github.sparkzxl.activiti.application.service.process.IProcessTaskStatusService;
import com.github.sparkzxl.activiti.domain.entity.DriveProcess;
import com.github.sparkzxl.activiti.infrastructure.strategy.AbstractActivitiSolver;
import com.github.sparkzxl.activiti.infrastructure.strategy.ActivitiSolverChooser;
import com.github.sparkzxl.activiti.infrastructure.utils.ActivitiUtils;
import com.github.sparkzxl.activiti.interfaces.dto.driver.DriveProcessDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessNextTaskDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.UserNextTask;
import com.github.sparkzxl.core.utils.ListUtils;
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
