package com.sparksys.activiti.domain.service;

import com.sparksys.activiti.application.service.process.IProcessTaskStatusService;
import com.sparksys.activiti.application.service.process.IProcessTaskService;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskStatus;
import com.sparksys.core.support.ResponseResultStatus;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    private IProcessTaskStatusService actHiTaskStatusService;

    public boolean promoteProcess(String userId, String processInstanceId, int actType, String message,
                                  Map<String, Object> variables) {
        Task task = processTaskService.getLatestTaskByProInstId(processInstanceId);
        String taskId = task.getId();
        ResponseResultStatus.FAILURE.assertNotNull(task);
        //添加审核人
        Authentication.setAuthenticatedUserId(String.valueOf(userId));
        if (StringUtils.isNotEmpty(message)) {
            processTaskService.addComment(taskId, processInstanceId, message);
        }
        processTaskService.claimTask(taskId, String.valueOf(userId));
        processTaskService.completeTask(taskId, variables);
        String taskDefinitionKey = task.getTaskDefinitionKey();
        return saveProcessTaskStatus(userId, processInstanceId, actType, taskDefinitionKey);
    }


    /**
     * 记录当前任务流程状态
     *
     * @param userId            用户id
     * @param processInstanceId 流程实例id
     * @param actType           流程动作类型
     * @param taskDefinitionKey 任务定义key
     * @return boolean
     */
    public boolean saveProcessTaskStatus(String userId, String processInstanceId, int actType, String taskDefinitionKey) {
        Task task = processTaskService.getLatestTaskByProInstId(processInstanceId);
        String taskId = task.getId();
        ResponseResultStatus.FAILURE.assertNotNull(task);
        //记录当前任务流程状态
        ProcessTaskStatus actHiTaskStatus = new ProcessTaskStatus();
        actHiTaskStatus.setProcessInstanceId(processInstanceId);
        actHiTaskStatus.setTaskId(taskId);
        actHiTaskStatus.setProcessStatus(actType);
        actHiTaskStatus.setCreateUser(Long.valueOf(userId));
        actHiTaskStatus.setUpdateUser(Long.valueOf(userId));
        actHiTaskStatus.setTaskDefKey(taskDefinitionKey);
        actHiTaskStatusService.save(actHiTaskStatus);
        return true;
    }

}
