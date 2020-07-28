package com.sparksys.activiti.interfaces.dto.driver;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

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


    @ApiModelProperty(value = "流程定义key", required = true)
    private String processDefinitionKey;

    @ApiModelProperty(value = "业务主键")
    protected String businessId;

    @ApiModelProperty(value = "流程动作类型")
    @NotNull(message = "流程动作类型不能为空")
    private Integer actType;

    @ApiModelProperty(value = "审核审批内容")
    private String comment;

    @ApiModelProperty(value = "任务处理人")
    private String applyUserId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "任务id")
    private String taskId;


}
