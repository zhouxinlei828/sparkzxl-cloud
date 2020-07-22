package com.sparksys.activiti.infrastructure.strategy;

import com.google.common.collect.Maps;
import com.sparksys.activiti.application.service.process.IProcessRepositoryService;
import com.sparksys.activiti.application.service.process.IProcessRuntimeService;
import com.sparksys.activiti.application.service.process.IProcessTaskRuleService;
import com.sparksys.activiti.application.service.process.IProcessTaskService;
import com.sparksys.activiti.domain.service.ActWorkApiService;
import com.sparksys.activiti.infrastructure.act.DeleteTaskCmd;
import com.sparksys.activiti.infrastructure.act.SetFlowNodeAndGoCmd;
import com.sparksys.activiti.infrastructure.constant.WorkflowConstants;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.activiti.domain.entity.DriveProcess;
import com.sparksys.activiti.infrastructure.utils.ActivitiUtils;
import com.sparksys.core.support.SparkSysExceptionAssert;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ManagementService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * description: 推动activiti流程
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:28:09
 */
@Component
@Slf4j
public class ActivitiJumpProcessSolver extends AbstractActivitiSolver {

    @Autowired
    private IProcessRuntimeService processRuntimeService;
    @Autowired
    private ActWorkApiService actWorkApiService;
    @Autowired
    private IProcessTaskService taskService;
    @Autowired
    private IProcessRepositoryService repositoryService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private IProcessTaskRuleService processTaskRuleService;

    @Override
    public boolean slove(DriveProcess driveProcess) {
        String businessId = driveProcess.getBusinessId();
        String applyUserId = driveProcess.getApplyUserId();
        String userId = driveProcess.getUserId();
        int actType = driveProcess.getActType();
        Map<String, Object> variables = Maps.newHashMap();
        if (StringUtils.isNotEmpty(applyUserId)) {
            variables.put("assignee", applyUserId);
        }
        variables.put("actType", actType);
        ProcessInstance processInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        Task currentTask = taskService.getTasksByAssigneeAndBusKey(applyUserId, businessId);
        BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        // 获取流程定义
        Process process = bpmnModel.getMainProcess();
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        // 获取目标节点task定义key
        ProcessTaskRule actRuTaskRule = processTaskRuleService.findActRuTaskRule(processDefinitionKey, currentTask.getTaskDefinitionKey(), actType);
        if (ObjectUtils.isEmpty(actRuTaskRule)) {
            SparkSysExceptionAssert.businessFail("请设置流程跳转规则");
        }
        String taskDefKey = actRuTaskRule.getTaskDefKey();
        FlowElement flowElement = ActivitiUtils.getFlowElementById(taskDefKey, flowElements);
        // 获取目标节点定义
        assert flowElement != null;
        FlowNode targetNode = (FlowNode) process.getFlowElement(flowElement.getId());
        // 删除当前运行任务，同时返回执行id，该id在并发情况下也是唯一的
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        // 流程执行到来源节点
        managementService.executeCommand(new SetFlowNodeAndGoCmd(targetNode, executionEntityId));
        actWorkApiService.saveProcessTaskStatus(userId,
                processInstance.getProcessInstanceId(),
                driveProcess.getActType(),
                currentTask.getTaskDefinitionKey());
        return true;
    }

    @Override
    public Integer[] supports() {
        return new Integer[]{WorkflowConstants.WorkflowAction.ROLLBACK, WorkflowConstants.WorkflowAction.JUMP};
    }
}
