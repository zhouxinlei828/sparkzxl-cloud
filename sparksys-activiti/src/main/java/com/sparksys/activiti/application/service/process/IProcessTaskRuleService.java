package com.sparksys.activiti.application.service.process;

import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.sparksys.activiti.interfaces.dto.process.TaskRuleUpdateDTO;
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

    /**
     * 保存流程跳转规则
     *
     * @param taskRuleSaveDTO 流程跳转规则保存实体类
     * @return boolean
     */
    boolean saveProcessTaskRule(TaskRuleSaveDTO taskRuleSaveDTO);

    /**
     * 查询流程跳转规则
     *
     * @param processId  流程定义key
     * @param taskDefKey 任务定义key
     * @return List<ProcessTaskRule>
     */
    List<ProcessTaskRule> getProcessTaskRule(String processId, String taskDefKey);

    /**
     * 修改流程跳转规则
     *
     * @param id                主键
     * @param taskRuleUpdateDTO 流程跳转更新对象
     * @return boolean
     */
    boolean updateProcessTaskRule(Long id, TaskRuleUpdateDTO taskRuleUpdateDTO);

}
