package com.sparksys.activiti.domain.repository;

import com.sparksys.activiti.infrastructure.entity.ActRuTaskRule;

import java.util.List;

/**
 * description: 流程控制规则 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 18:19:15
*/
public interface IActRuTaskRuleRepository {

    /**
     * 查询任务流程控制列表
     *
     * @param taskId 任务id
     * @return List<ActRuTaskRule>
     */
    List<ActRuTaskRule> findActRuTaskRuleByTaskId(String taskId);
}
