package com.sparksys.activiti.domain.repository;

import com.sparksys.activiti.infrastructure.entity.ProcessInstance;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskStatus;

import java.util.List;

/**
 * description: 流程状态 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-23 13:58:34
 */
public interface IProcessTaskStatusRepository {

    /**
     * 根据流程实例id查询流程状态
     *
     * @param processInstanceId 流程实例id
     * @return ProcessTaskStatus
     */
    ProcessTaskStatus getProcessTaskStatus(String processInstanceId);

    /**
     * 查询流程实例列表
     *
     * @param name 流程名称
     * @return List<ProcessInstance>
     */
    List<ProcessInstance> getProcessInstanceList(String name);
}
