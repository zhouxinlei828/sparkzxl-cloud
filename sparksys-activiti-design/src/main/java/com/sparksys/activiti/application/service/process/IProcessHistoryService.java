package com.sparksys.activiti.application.service.process;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
     * 获取流程历史中已执行节点
     *
     * @param processInstanceId 流程实例id
     * @return List<HistoricActivityInstance>
     */
    List<HistoricActivityInstance> getHistoricActivityInstance(String processInstanceId);

    /**
     * 获取流程图
     *
     * @param processInstanceId 流程实例id
     * @param response          返回响应
     */
    void getProcessImage(String processInstanceId, HttpServletResponse response);
}
