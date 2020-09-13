package com.github.sparkzxl.activiti.interfaces.dto.process;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * description: 流程驱动入参
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:00:12
 */
@Getter
@Setter
@ToString
@ApiModel("下一步任务查询入参")
public class ProcessNextTaskDTO {

    @ApiModelProperty(value = "流程定义id", required = true)
    private String processDefinitionId;

    @ApiModelProperty(value = "任务定义key")
    private String taskDefKey;

    @ApiModelProperty(value = "业务数据")
    Map<String, Object> variables;

}
