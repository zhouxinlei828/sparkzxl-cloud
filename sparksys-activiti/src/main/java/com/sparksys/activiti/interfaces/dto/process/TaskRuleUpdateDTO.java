package com.sparksys.activiti.interfaces.dto.process;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 14:56:57
 */
@Data
@ApiModel(value = "TaskRule更新对象", description = "")
public class TaskRuleUpdateDTO {

    @ApiModelProperty(value = "流程详细id")
    @NotNull(message = "流程详细id不能为空")
    private Long processDetailId;

    @ApiModelProperty(value = "目标任务定义key")
    @NotNull(message = "目标任务不能为空")
    private String taskDefKey;

    @ApiModelProperty(value = "流程类型")
    @NotNull(message = "流程类型不能为空")
    private Integer actType;

}
