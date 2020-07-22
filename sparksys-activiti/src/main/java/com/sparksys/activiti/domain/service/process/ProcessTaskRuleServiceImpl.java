package com.sparksys.activiti.domain.service.process;

import com.sparksys.activiti.domain.repository.IActRuTaskRuleRepository;
import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskRuleMapper;
import com.sparksys.activiti.application.service.process.IProcessTaskRuleService;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:流程控制 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:00
 */
@Service
public class ProcessTaskRuleServiceImpl extends AbstractSuperCacheServiceImpl<ProcessTaskRuleMapper, ProcessTaskRule> implements IProcessTaskRuleService {

    private final IActRuTaskRuleRepository actRuTaskRuleRepository;

    public ProcessTaskRuleServiceImpl(IActRuTaskRuleRepository actRuTaskRuleRepository) {
        this.actRuTaskRuleRepository = actRuTaskRuleRepository;
    }

    @Override
    public ProcessTaskRule findActRuTaskRule(String processDefinitionKey, String sourceTaskDefKey, Integer actType) {
        return actRuTaskRuleRepository.findActRuTaskRule(processDefinitionKey,sourceTaskDefKey, actType);
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_CONTROL;
    }
}
