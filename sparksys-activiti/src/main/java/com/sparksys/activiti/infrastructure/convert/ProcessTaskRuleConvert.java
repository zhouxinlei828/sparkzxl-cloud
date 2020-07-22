package com.sparksys.activiti.infrastructure.convert;

import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.sparksys.activiti.interfaces.dto.process.TaskRuleUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: ProcessTaskRule 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface ProcessTaskRuleConvert {

    ProcessTaskRuleConvert INSTANCE = Mappers.getMapper(ProcessTaskRuleConvert.class);

    ProcessTaskRule convertTaskRuleSaveDTO(TaskRuleSaveDTO taskRuleSaveDTO);

    ProcessTaskRule convertTaskRuleUpdateDTO(TaskRuleUpdateDTO taskRuleUpdateDTO);

}
