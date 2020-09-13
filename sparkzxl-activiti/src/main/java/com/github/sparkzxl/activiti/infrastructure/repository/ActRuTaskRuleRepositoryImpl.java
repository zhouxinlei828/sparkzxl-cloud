package com.github.sparkzxl.activiti.infrastructure.repository;

import com.github.sparkzxl.activiti.domain.repository.IActRuTaskRuleRepository;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskRule;
import com.github.sparkzxl.activiti.infrastructure.mapper.ProcessTaskRuleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 流程控制规则 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-22 10:03:43
 */
@Repository
public class ActRuTaskRuleRepositoryImpl implements IActRuTaskRuleRepository {

    private final ProcessTaskRuleMapper actRuTaskRuleMapper;

    public ActRuTaskRuleRepositoryImpl(ProcessTaskRuleMapper actRuTaskRuleMapper) {
        this.actRuTaskRuleMapper = actRuTaskRuleMapper;
    }

    @Override
    public ProcessTaskRule findActRuTaskRule(String processDefinitionKey, String sourceTaskDefKey, Integer actType) {
        return actRuTaskRuleMapper.findActRuTaskRule(processDefinitionKey, sourceTaskDefKey, actType);
    }

    @Override
    public List<ProcessTaskRule> getProcessTaskRule(String processId, String taskDefKey) {
        return actRuTaskRuleMapper.getProcessTaskRule(processId, taskDefKey);
    }
}
