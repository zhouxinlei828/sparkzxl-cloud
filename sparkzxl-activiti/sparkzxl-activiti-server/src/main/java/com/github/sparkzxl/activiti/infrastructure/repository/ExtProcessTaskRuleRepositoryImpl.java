package com.github.sparkzxl.activiti.infrastructure.repository;

import com.github.sparkzxl.activiti.domain.repository.IExtProcessTaskRuleRepository;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessTaskRule;
import com.github.sparkzxl.activiti.infrastructure.mapper.ExtProcessTaskRuleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 流程控制规则 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-22 10:03:43
 */
@Repository
public class ExtProcessTaskRuleRepositoryImpl implements IExtProcessTaskRuleRepository {

    private final ExtProcessTaskRuleMapper extProcessTaskRuleMapper;

    public ExtProcessTaskRuleRepositoryImpl(ExtProcessTaskRuleMapper extProcessTaskRuleMapper) {
        this.extProcessTaskRuleMapper = extProcessTaskRuleMapper;
    }

    @Override
    public ExtProcessTaskRule findActRuTaskRule(String processDefinitionKey, String sourceTaskDefKey, Integer actType) {
        return extProcessTaskRuleMapper.findActRuTaskRule(processDefinitionKey, sourceTaskDefKey, actType);
    }

    @Override
    public List<ExtProcessTaskRule> getProcessTaskRule(String processDefinitionKey, String taskDefKey) {
        return extProcessTaskRuleMapper.getProcessTaskRule(processDefinitionKey, taskDefKey);
    }
}
