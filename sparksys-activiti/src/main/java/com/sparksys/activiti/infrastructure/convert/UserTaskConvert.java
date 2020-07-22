package com.sparksys.activiti.infrastructure.convert;

import com.sparksys.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.sparksys.activiti.interfaces.dto.process.UserNextTask;
import org.activiti.bpmn.model.UserTask;
import org.mapstruct.factory.Mappers;

/**
 * description: UserTask 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
public interface UserTaskConvert {

    UserTaskConvert INSTANCE = Mappers.getMapper(UserTaskConvert.class);

    UserNextTask convertUserNextTask(UserTask userTask);
}
