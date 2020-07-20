package com.sparksys.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.activiti.domain.repository.IActRuTaskRuleRepository;
import com.sparksys.activiti.infrastructure.entity.ActRuTaskRule;
import com.sparksys.activiti.infrastructure.mapper.ActRuTaskRuleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActRuTaskRuleRepositoryImpl implements IActRuTaskRuleRepository {

    private final ActRuTaskRuleMapper actRuTaskRuleMapper;

    public ActRuTaskRuleRepositoryImpl(ActRuTaskRuleMapper actRuTaskRuleMapper) {
        this.actRuTaskRuleMapper = actRuTaskRuleMapper;
    }

    @Override
    public List<ActRuTaskRule> findActRuTaskRuleByTaskId(String taskId) {
        return actRuTaskRuleMapper.selectList(new QueryWrapper<ActRuTaskRule>().lambda().eq(ActRuTaskRule::getTaskId,taskId));
    }
}
