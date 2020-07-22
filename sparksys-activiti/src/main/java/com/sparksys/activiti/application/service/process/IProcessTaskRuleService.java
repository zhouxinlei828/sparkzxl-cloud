package com.sparksys.activiti.application.service.process;

import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.database.service.SuperCacheService;

import java.util.List;

/**
 * description: 流程跳转控制 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:20:37
 */
public interface IProcessTaskRuleService extends SuperCacheService<ProcessTaskRule> {


    /**
     * 查询任务流程控制规则
     *
     * @param processDefinitionKey 流程定义key
     * @param sourceTaskDefKey     源任务定义key
     * @param actType              流程类型
     * @return ProcessTaskRule
     */
    ProcessTaskRule findActRuTaskRule(String processDefinitionKey, String sourceTaskDefKey, Integer actType);
}
