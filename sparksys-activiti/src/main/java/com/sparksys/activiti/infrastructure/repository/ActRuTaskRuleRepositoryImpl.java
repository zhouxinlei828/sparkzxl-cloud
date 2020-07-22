package com.sparksys.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.activiti.domain.repository.IActRuTaskRuleRepository;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskRuleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
