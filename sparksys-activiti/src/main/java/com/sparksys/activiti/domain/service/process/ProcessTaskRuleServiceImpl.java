package com.sparksys.activiti.domain.service.process;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sparksys.activiti.domain.repository.IActRuTaskRuleRepository;
import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.constant.WorkflowConstants;
import com.sparksys.activiti.infrastructure.convert.ProcessTaskRuleConvert;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskRuleMapper;
import com.sparksys.activiti.application.service.process.IProcessTaskRuleService;
import com.sparksys.activiti.interfaces.dto.process.ProcessActionDTO;
import com.sparksys.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return actRuTaskRuleRepository.findActRuTaskRule(processDefinitionKey, sourceTaskDefKey, actType);
    }

    @Override
    public boolean saveProcessTaskRule(TaskRuleSaveDTO taskRuleSaveDTO) {
        ProcessTaskRule processTaskRule = ProcessTaskRuleConvert.INSTANCE.convertTaskRuleSaveDTO(taskRuleSaveDTO);
        return saveOrUpdate(processTaskRule);
    }

    @Override
    public List<ProcessTaskRule> getProcessTaskRule(String processId, String taskDefKey) {
        return actRuTaskRuleRepository.getProcessTaskRule(processId, taskDefKey);
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
