package com.sparksys.activiti.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import com.sparksys.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 数据集
 *
 * @author: zhouxinlei
 * @date: 2020-07-16 18:40:08
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("process_dataset")
@ApiModel(value="ProcessDataset对象", description="数据集")
public class ProcessDataset extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据集申请人")
    @TableField("creator")
    private String creator;

    @ApiModelProperty(value = "流程状态")
    @TableField("process_status")
    private Integer processStatus;

    @ApiModelProperty(value = "数据集ID")
    @TableField("dataset_id")
    private Integer datasetId;

    @ApiModelProperty(value = "流程实例ID")
    @TableField("proc_inst_id")
    private String procInstId;

    @ApiModelProperty(value = "数据集名称")
    @TableField("dataset_name")
    private String datasetName;

    @ApiModelProperty(value = "申请任务的优先级")
    @TableField("priority")
    private Integer priority;

}
