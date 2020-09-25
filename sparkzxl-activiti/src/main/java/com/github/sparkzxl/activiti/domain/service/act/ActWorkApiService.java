package com.github.sparkzxl.activiti.domain.service.act;

import com.github.sparkzxl.activiti.application.service.act.IProcessRuntimeService;
import com.github.sparkzxl.activiti.application.service.act.IProcessTaskService;
import com.github.sparkzxl.activiti.application.service.process.*;
import com.github.sparkzxl.activiti.infrastructure.entity.ActHiTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.enums.ProcessStatusEnum;
import com.github.sparkzxl.activiti.infrastructure.enums.TaskStatusEnum;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private IProcessTaskStatusService processTaskStatusService;
    @Autowired
    private IActHiTaskStatusService actHiTaskStatusService;

    public boolean promoteProcess(String userId, String processInstanceId, String businessId, int actType, String message,
                                  Map<String, Object> variables) {
        Task task = processTaskService.getLatestTaskByProInstId(processInstanceId);
        String taskId = task.getId();
        String taskDefinitionKey = task.getTaskDefinitionKey();
        ResponseResultStatus.FAILURE.assertNotNull(task);
        Object assignee = variables.get("assignee");
        //添加审核人
        Authentication.setAuthenticatedUserId(String.valueOf(assignee));
        if (StringUtils.isNotEmpty(message)) {
            processTaskService.addComment(taskId, processInstanceId, message);
        }
        processTaskService.claimTask(taskId, String.valueOf(assignee));
        processTaskService.completeTask(taskId, variables);
        String processStatus;
        if (processRuntimeService.processIsEnd(processInstanceId)) {
            processStatus = ProcessStatusEnum.END.getDesc();
        } else {
            processStatus = ProcessStatusEnum.SUBMIT.getDesc();
        }
        CompletableFuture.runAsync(() -> saveProcessTaskStatus(userId,
                processInstanceId,
                businessId,
                processStatus));
        CompletableFuture.runAsync(() -> saveActHiTaskStatus(userId, processInstanceId,
                taskId, taskDefinitionKey, TaskStatusEnum.getValue(actType)));
        return true;
    }


    /**
     * 保存任务历史记录
     *
     * @param userId            用户id
     * @param processInstanceId 流程实例id
     * @param taskId            任务id
     * @param taskDefinitionKey 任务定义key
     * @param taskStatus        任务状态
     */
    public void saveActHiTaskStatus(String userId, String processInstanceId, String taskId, String taskDefinitionKey, String taskStatus) {
        ActHiTaskStatus actHiTaskStatus = new ActHiTaskStatus();
        actHiTaskStatus.setProcessInstanceId(processInstanceId);
        actHiTaskStatus.setTaskId(taskId);
        actHiTaskStatus.setTaskDefKey(taskDefinitionKey);
        actHiTaskStatus.setCreateUser(Long.valueOf(userId));
        actHiTaskStatus.setUpdateUser(Long.valueOf(userId));
        actHiTaskStatus.setTaskStatus(taskStatus);
        actHiTaskStatusService.save(actHiTaskStatus);
    }

    /**
     * 记录当前任务流程状态
     *
     * @param userId            用户id
     * @param processInstanceId 流程实例id
     * @param processStatus     流程状态
     */
    public void saveProcessTaskStatus(String userId, String processInstanceId, String businessId, String processStatus) {

        ProcessTaskStatus actHiTaskStatus = processTaskStatusService.getProcessTaskStatus(processInstanceId);
        //记录当前任务流程状态
        if (ObjectUtils.isNotEmpty(actHiTaskStatus)) {
            actHiTaskStatus.setProcessStatus(processStatus);
        } else {
            actHiTaskStatus = new ProcessTaskStatus();
            actHiTaskStatus.setProcessInstanceId(processInstanceId);
            actHiTaskStatus.setBusinessId(businessId);
            actHiTaskStatus.setProcessStatus(processStatus);
            actHiTaskStatus.setCreateUser(Long.valueOf(userId));
        }
        actHiTaskStatus.setUpdateUser(Long.valueOf(userId));
        processTaskStatusService.saveOrUpdate(actHiTaskStatus);
    }

}
