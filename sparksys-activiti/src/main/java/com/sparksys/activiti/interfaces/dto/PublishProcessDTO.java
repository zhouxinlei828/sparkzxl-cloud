package com.sparksys.activiti.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:创建模型入参
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 09:19:11
 */
@Data
@ApiModel("发布流程入参")
public class PublishProcessDTO {

    @ApiModelProperty(value = "模型名称", required = true)
    @NotNull(message = "模型名称不能为空")
    private String name;

    @ApiModelProperty(value = "模型key", required = true)
    @NotNull(message = "模型key不能为空")
    private String key;
}
