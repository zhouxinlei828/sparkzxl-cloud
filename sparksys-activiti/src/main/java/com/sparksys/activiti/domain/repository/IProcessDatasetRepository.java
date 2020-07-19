package com.sparksys.activiti.domain.repository;

import com.sparksys.activiti.infrastructure.entity.ActivitiTask;

import java.util.List;

/**
 * description: 数据集仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:13:36
 */
public interface IProcessDatasetRepository {
    /**
     * 获取用户需要处理的任务
     *
     * @param assignee 用户id
     * @return List<ActivitiTask>
     */
    List<ActivitiTask> getTasksByAssignee(String assignee);
}
