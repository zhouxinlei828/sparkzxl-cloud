package com.sparksys.activiti.domain.repository;

import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;

import java.util.List;

/**
 * description: 流程控制规则 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 18:19:15
 */
public interface IActRuTaskRuleRepository {

    /**
     * 查询任务流程控制规则
     *
     * @param processDefinitionKey 流程定义key
     * @param sourceTaskDefKey     源任务定义key
     * @param actType              流程类型
     * @return ProcessTaskRule
     */
    ProcessTaskRule findActRuTaskRule(String processDefinitionKey, String sourceTaskDefKey, Integer actType);

    /**
     * 查询流程跳转规则
     *
     * @param processId  流程定义key
     * @param taskDefKey 任务定义key
     * @return List<ProcessTaskRule>
     */
    List<ProcessTaskRule> getProcessTaskRule(String processId, String taskDefKey);
}
