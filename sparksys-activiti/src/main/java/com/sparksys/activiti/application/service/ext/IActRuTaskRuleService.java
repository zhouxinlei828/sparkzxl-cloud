package com.sparksys.activiti.application.service.ext;

import com.sparksys.activiti.infrastructure.entity.ActRuTaskRule;
import com.sparksys.database.service.SuperCacheService;

import java.util.List;

/**
 * description: 流程控制 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:20:37
 */
public interface IActRuTaskRuleService extends SuperCacheService<ActRuTaskRule> {


    /**
     * 查询任务流程控制列表
     *
     * @param taskId 任务id
     * @return List<ActRuTaskRule>
     */
    List<ActRuTaskRule> findActRuTaskRuleByTaskId(String taskId);
}
