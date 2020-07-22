package com.sparksys.activiti.domain.service.process;

import com.sparksys.activiti.domain.repository.IActRuTaskRuleRepository;
import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.convert.ProcessTaskRuleConvert;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskRuleMapper;
import com.sparksys.activiti.application.service.process.IProcessTaskRuleService;
import com.sparksys.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.sparksys.activiti.interfaces.dto.process.TaskRuleUpdateDTO;
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
        return actRuTaskRuleRepository.findActRuTaskRule(processDefinitionKey, sourceTaskDefKey, actType);
    }

    @Override
    public boolean saveProcessTaskRule(TaskRuleSaveDTO taskRuleSaveDTO) {
        ProcessTaskRule processTaskRule = ProcessTaskRuleConvert.INSTANCE.convertTaskRuleSaveDTO(taskRuleSaveDTO);
        return save(processTaskRule);
    }

    @Override
    public List<ProcessTaskRule> getProcessTaskRule(String processId, String taskDefKey) {
        return actRuTaskRuleRepository.getProcessTaskRule(processId, taskDefKey);
    }

    @Override
    public boolean updateProcessTaskRule(Long id, TaskRuleUpdateDTO taskRuleUpdateDTO) {
        ProcessTaskRule processTaskRule = ProcessTaskRuleConvert.INSTANCE.convertTaskRuleUpdateDTO(taskRuleUpdateDTO);
        processTaskRule.setId(id);
        return updateById(processTaskRule);
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_CONTROL;
    }
}
