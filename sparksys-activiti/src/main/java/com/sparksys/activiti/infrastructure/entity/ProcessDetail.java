package com.sparksys.activiti.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.annotation.TableField;

import com.sparksys.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 流程详细节点
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 14:20:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("process_detail")
@ApiModel(value = "ProcessDetail对象", description = "")
public class ProcessDetail extends Entity<Long> {

    private static final long serialVersionUID = -8334040441264344916L;

    @ApiModelProperty(value = "模型id")
    @TableField("model_id")
    private String modelId;

    @ApiModelProperty(value = "流程定义key")
    @TableField("process_id")
    private String processId;

    @ApiModelProperty(value = "流程名称")
    @TableField("process_name")
    private String processName;

    @ApiModelProperty(value = "任务定义key")
    @TableField("task_def_key")
    private String taskDefKey;

    @ApiModelProperty(value = "任务名称")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "任务类型")
    @TableField("type")
    private String type;

}
