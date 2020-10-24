package com.github.sparkzxl.activiti.domain.service.act;

import com.github.sparkzxl.activiti.application.service.act.IProcessRepositoryService;
import com.github.sparkzxl.activiti.application.service.act.IProcessRuntimeService;
import com.github.sparkzxl.activiti.application.service.act.IProcessTaskService;
import com.github.sparkzxl.activiti.application.service.ext.IExtHiTaskStatusService;
import com.github.sparkzxl.activiti.application.service.ext.IExtProcessTaskRuleService;
import com.github.sparkzxl.activiti.application.service.ext.IExtProcessStatusService;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.infrastructure.act.DeleteTaskCmd;
import com.github.sparkzxl.activiti.infrastructure.act.SetFlowNodeAndGoCmd;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtHiTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessTaskRule;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessStatus;
import com.github.sparkzxl.activiti.infrastructure.enums.ProcessStatusEnum;
import com.github.sparkzxl.activiti.infrastructure.enums.TaskStatusEnum;
import com.github.sparkzxl.activiti.infrastructure.utils.ActivitiUtils;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ManagementService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * description: 流程核心API接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 18:35:56
 */
@Service
public class ActWorkApiService {

    @Autowired
    private IProcessTaskService processTaskService;
    @Autowired
    private IProcessRuntimeService processRuntimeService;
    @Autowired
    private IExtProcessStatusService processTaskStatusService;
    @Autowired
    private IExtHiTaskStatusService actHiTaskStatusService;
    @Autowired
    private IProcessTaskService taskService;
    @Autowired
    private IProcessRepositoryService repositoryService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private IExtProcessTaskRuleService processTaskRuleService;

    public DriverResult promoteProcess(String userId, String processInstanceId, String businessId, int actType, String message,
                                       Map<String, Object> variables) {
        Task task = processTaskService.getLatestTaskByProInstId(processInstanceId);
        String taskId = task.getId();
        String taskDefinitionKey = task.getTaskDefinitionKey();
        ResponseResultStatus.FAILURE.assertNotNull(task);
        //添加审核人
        Authentication.setAuthenticatedUserId(userId);
        if (StringUtils.isNotEmpty(message)) {
            processTaskService.addComment(taskId, processInstanceId, message);
        }
        processTaskService.setAssignee(taskId, userId);
        processTaskService.claimTask(taskId, userId);
        processTaskService.completeTask(taskId, variables);
        DriverResult driverResult = new DriverResult();
        boolean processIsEnd = processRuntimeService.processIsEnd(processInstanceId);
        driverResult.setProcessIsEnd(processIsEnd);
        String status;
        if (processIsEnd) {
            status = ProcessStatusEnum.END.getDesc();
        } else {
            status = ProcessStatusEnum.SUBMIT.getDesc();
        }
        driverResult.setOperateSuccess(true);
        CompletableFuture.runAsync(() -> saveProcessTaskStatus(
                processInstanceId,
                businessId,
                status));
        CompletableFuture.runAsync(() -> saveActHiTaskStatus(
                processInstanceId,
                taskId,
                taskDefinitionKey,
                TaskStatusEnum.getValue(actType)));
        return driverResult;
    }

    public DriverResult jumpProcess(String processInstanceId, String processDefinitionKey, String businessId, int actType) {
        Task currentTask = taskService.getLatestTaskByProInstId(processInstanceId);
        String taskDefinitionKey = currentTask.getTaskDefinitionKey();
        String taskId = currentTask.getId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        // 获取流程定义
        Process process = bpmnModel.getMainProcess();
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();
        // 获取目标节点task定义key
        ExtProcessTaskRule actRuTaskRule = processTaskRuleService.findActRuTaskRule(processDefinitionKey, currentTask.getTaskDefinitionKey(), actType);
        if (ObjectUtils.isEmpty(actRuTaskRule)) {
            SparkZxlExceptionAssert.businessFail("请设置流程跳转规则");
        }
        String taskDefKey = actRuTaskRule.getTaskDefKey();
        String taskName = actRuTaskRule.getTaskName();
        FlowElement flowElement = ActivitiUtils.getFlowElementById(taskDefKey, flowElements);
        // 获取目标节点定义
        assert flowElement != null;
        FlowNode targetNode = (FlowNode) process.getFlowElement(flowElement.getId());
        // 删除当前运行任务，同时返回执行id，该id在并发情况下也是唯一的
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        // 流程执行到来源节点
        managementService.executeCommand(new SetFlowNodeAndGoCmd(targetNode, executionEntityId));
        boolean processIsEnd = processRuntimeService.processIsEnd(processInstanceId);
        String status;
        if (WorkflowConstants.WorkflowAction.ROLLBACK == actType) {
            status = ProcessStatusEnum.getValue(actType);
        } else if (processIsEnd) {
            status = ProcessStatusEnum.END.getDesc();
        } else {
            status = ProcessStatusEnum.SUBMIT.getDesc();
        }
        DriverResult driverResult = new DriverResult();
        driverResult.setProcessIsEnd(processIsEnd);
        CompletableFuture.runAsync(() -> saveProcessTaskStatus(
                processInstanceId,
                businessId,
                status));
        CompletableFuture.runAsync(() -> saveActHiTaskStatus(processInstanceId,
                taskId, taskDefinitionKey, TaskStatusEnum.getValue(actType)));
        driverResult.setOperateSuccess(true);
        return driverResult;
    }

    /**
     * 保存任务历史记录
     *
     * @param processInstanceId 流程实例id
     * @param taskId            任务id
     * @param taskDefinitionKey 任务定义key
     * @param taskStatus        任务状态
     */
    public void saveActHiTaskStatus(String processInstanceId, String taskId, String taskDefinitionKey, String taskStatus) {
        ExtHiTaskStatus actHiTaskStatus = new ExtHiTaskStatus();
        actHiTaskStatus.setProcessInstanceId(processInstanceId);
        actHiTaskStatus.setTaskId(taskId);
        actHiTaskStatus.setTaskDefKey(taskDefinitionKey);
        actHiTaskStatus.setTaskStatus(taskStatus);
        actHiTaskStatusService.save(actHiTaskStatus);
    }

    /**
     * 记录当前任务流程状态
     *
     * @param processInstanceId 流程实例id
     * @param status            流程状态
     */
    public void saveProcessTaskStatus(String processInstanceId, String businessId, String status) {
        ExtProcessStatus actHiTaskStatus = processTaskStatusService.getExtProcessStatus(businessId);
        //记录当前任务流程状态
        if (ObjectUtils.isNotEmpty(actHiTaskStatus)) {
            actHiTaskStatus.setStatus(status);
        } else {
            actHiTaskStatus = new ExtProcessStatus();
            actHiTaskStatus.setProcessInstanceId(processInstanceId);
            actHiTaskStatus.setBusinessId(businessId);
            actHiTaskStatus.setStatus(status);
        }
        processTaskStatusService.saveOrUpdate(actHiTaskStatus);
    }

}
