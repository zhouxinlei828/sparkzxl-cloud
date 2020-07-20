package com.sparksys.activiti.interfaces.dto;

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
@ApiModel("流程驱动入参")
public class DriveProcessDTO {


    @ApiModelProperty(value = "流程图id", required = true)
    private String bpmnId;

    @ApiModelProperty(value = "流程变量")
    Map<String, Object> variables;

    @ApiModelProperty(value = "业务主键")
    protected String businessId;

    @ApiModelProperty(value = "流程走向条件")
    private int actType;

    private String comment;

}
