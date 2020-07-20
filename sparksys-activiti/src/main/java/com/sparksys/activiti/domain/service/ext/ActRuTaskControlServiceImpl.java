package com.sparksys.activiti.domain.service.ext;

import com.sparksys.activiti.domain.repository.IActRuTaskRuleRepository;
import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ActRuTaskRule;
import com.sparksys.activiti.infrastructure.mapper.ActRuTaskRuleMapper;
import com.sparksys.activiti.application.service.ext.IActRuTaskRuleService;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.database.utils.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:流程控制 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:00
 */
@Service
public class ActRuTaskControlServiceImpl extends AbstractSuperCacheServiceImpl<ActRuTaskRuleMapper, ActRuTaskRule> implements IActRuTaskRuleService {

    private final IActRuTaskRuleRepository actRuTaskRuleRepository;

    public ActRuTaskControlServiceImpl(IActRuTaskRuleRepository actRuTaskRuleRepository) {
        this.actRuTaskRuleRepository = actRuTaskRuleRepository;
    }

    @Override
    public List<ActRuTaskRule> findActRuTaskRuleByTaskId(String taskId) {
        List<ActRuTaskRule> actRuTaskRules = actRuTaskRuleRepository.findActRuTaskRuleByTaskId(taskId);
        return TreeUtil.buildTree(actRuTaskRules);
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_CONTROL;
    }
}
