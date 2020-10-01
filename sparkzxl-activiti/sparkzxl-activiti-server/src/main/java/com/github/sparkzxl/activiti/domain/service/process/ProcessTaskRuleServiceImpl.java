package com.github.sparkzxl.activiti.domain.service.process;

import com.github.sparkzxl.activiti.application.service.process.IProcessTaskRuleService;
import com.github.sparkzxl.activiti.domain.repository.IActRuTaskRuleRepository;
import com.github.sparkzxl.activiti.infrastructure.constant.ActivitiCache;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import com.github.sparkzxl.activiti.infrastructure.convert.ProcessTaskRuleConvert;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskRule;
import com.github.sparkzxl.activiti.infrastructure.mapper.ProcessTaskRuleMapper;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessActionDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
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
