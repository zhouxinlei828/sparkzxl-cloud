package com.github.sparkzxl.activiti.domain.service.ext;

import com.github.sparkzxl.activiti.application.service.ext.IExtProcessTaskRuleService;
import com.github.sparkzxl.activiti.domain.repository.IExtProcessTaskRuleRepository;
import com.github.sparkzxl.activiti.infrastructure.constant.ActivitiCache;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import com.github.sparkzxl.activiti.infrastructure.convert.ProcessTaskRuleConvert;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessTaskRule;
import com.github.sparkzxl.activiti.infrastructure.mapper.ExtProcessTaskRuleMapper;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessActionDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.github.sparkzxl.database.base.service.impl.SuperCacheServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:流程控制 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:00
 */
@Service
public class ExtProcessTaskRuleServiceImpl extends SuperCacheServiceImpl<ExtProcessTaskRuleMapper, ExtProcessTaskRule> implements IExtProcessTaskRuleService {

    private final IExtProcessTaskRuleRepository actRuTaskRuleRepository;

    public ExtProcessTaskRuleServiceImpl(IExtProcessTaskRuleRepository actRuTaskRuleRepository) {
        this.actRuTaskRuleRepository = actRuTaskRuleRepository;
    }

    @Override
    public ExtProcessTaskRule findActRuTaskRule(String processDefinitionKey, String sourceTaskDefKey, Integer actType) {
        return actRuTaskRuleRepository.findActRuTaskRule(processDefinitionKey, sourceTaskDefKey, actType);
    }

    @Override
    public boolean saveProcessTaskRule(TaskRuleSaveDTO taskRuleSaveDTO) {
        ExtProcessTaskRule processTaskRule = ProcessTaskRuleConvert.INSTANCE.convertTaskRuleSaveDTO(taskRuleSaveDTO);
        return saveOrUpdate(processTaskRule);
    }

    @Override
    public List<ExtProcessTaskRule> getProcessTaskRule(String processDefinitionKey, String taskDefKey) {
        return actRuTaskRuleRepository.getProcessTaskRule(processDefinitionKey, taskDefKey);
    }

    @Override
    public List<ProcessActionDTO> getProcessAction() {
        List<ProcessActionDTO> processActions = Lists.newArrayList();
        processActions.add(ProcessActionDTO.builder()
                .id(WorkflowConstants.WorkflowAction.JUMP)
                .name("跳转")
                .build());
        processActions.add(ProcessActionDTO.builder()
                .id(WorkflowConstants.WorkflowAction.ROLLBACK)
                .name("驳回")
                .build());
        return processActions;
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_CONTROL;
    }
}
