package com.github.sparkzxl.activiti.application.service.act;

import com.github.sparkzxl.activiti.infrastructure.entity.ProcessHistory;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * description: 历史流程 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 15:17:28
 */
public interface IProcessHistoryService {

    /**
     * 获取历史流程实例
     *
     * @param processInstanceId 流程实例id
     * @return HistoricProcessInstance
     */
    HistoricProcessInstance getHistoricProcessInstance(String processInstanceId);

    /**
     * 根据签收人id获取历史任务列表
     *
     * @param assignee 签收人id
     * @return List<HistoricTaskInstance>
     */
    List<HistoricTaskInstance> getHistoricTasksByAssigneeId(String assignee);

    /**
     * 根据流程实例id获取历史任务列表
     *
     * @param processInstanceId 流程实例id
     * @return List<HistoricTaskInstance>
     */
    List<HistoricTaskInstance> getHistoricTasksByProcessInstanceId(String processInstanceId);

    /**
     * 根据流程实例id获取历史任务列表
     *
     * @param taskId 任务id
     * @return HistoricTaskInstance
     */
    HistoricTaskInstance getHistoricTasksByTaskId(String taskId);

    /**
     * 根据业务主键获取流程历史
     *
     * @param businessId 业务主键
     * @return List<ProcessHistory>
     * @throws ExecutionException   运行异常
     * @throws InterruptedException 中断异常
     */
    List<ProcessHistory> getProcessHistoryByBusinessId(String businessId) throws ExecutionException, InterruptedException;

    /**
     * 根据流程实例id获取流程历史
     *
     * @param processInstanceId 流程实例id
     * @return List<ProcessHistory>
     * @throws ExecutionException   运行异常
     * @throws InterruptedException 中断异常
     */
    List<ProcessHistory> getProcessHistoryByProcessInstanceId(String processInstanceId) throws ExecutionException, InterruptedException;

    /**
     * 获取流程图
     *
     * @param processInstanceId 流程实例id
     * @param response          返回响应
     */
    void getProcessImage(String processInstanceId, HttpServletResponse response);
}
