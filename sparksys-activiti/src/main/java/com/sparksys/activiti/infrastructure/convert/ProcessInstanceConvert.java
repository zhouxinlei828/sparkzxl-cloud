package com.sparksys.activiti.infrastructure.convert;

import com.sparksys.activiti.infrastructure.entity.ProcessInstance;
import com.sparksys.activiti.interfaces.dto.act.ProcessInstanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: ProcessInstance 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface ProcessInstanceConvert {

    ProcessInstanceConvert INSTANCE = Mappers.getMapper(ProcessInstanceConvert.class);

    ProcessInstanceDTO convertProcessInstanceDTO(ProcessInstance processInstance);

}
