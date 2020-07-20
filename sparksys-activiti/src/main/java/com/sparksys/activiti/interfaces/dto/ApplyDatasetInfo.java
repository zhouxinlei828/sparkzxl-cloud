package com.sparksys.activiti.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * description: 申请数据集的基本信息
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 15:24:57
 */
@Getter
@Setter
@ToString
@ApiModel("申请数据集参数")
public class ApplyDatasetInfo {

    @ApiModelProperty(value = "数据集ID", required = true)
    @NotNull
    protected Integer dataSetId;

    @ApiModelProperty(value = "数据集名称", required = true)
    @NotNull
    protected String dataSetName;

    @ApiModelProperty(value = "数据集创建人", required = true)
    @NotNull
    protected String dataSetCreator;

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
