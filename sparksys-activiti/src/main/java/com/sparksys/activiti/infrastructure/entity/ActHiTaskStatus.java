package com.sparksys.activiti.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import com.sparksys.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 任务历史状态记录（新加表）
 *
 * @author: zhouxinlei
 * @date: 2020-07-16 18:39:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("act_hi_task_status")
@ApiModel(value = "ActHiTaskStatus对象", description = "")
public class ActHiTaskStatus extends Entity<Long> {

    private static final long serialVersionUID = 7233248562949888216L;

    @ApiModelProperty(value = "流程实例id")
    @TableField("process_instance_id")
    private String processInstanceId;

    @ApiModelProperty(value = "任务id")
    @TableField("task_id")
    private String taskId;

    @ApiModelProperty(value = "任务定义key")
    @TableField("task_def_key")
    private String taskDefKey;

    @ApiModelProperty(value = "任务状态")
    @TableField("task_status")
    private String taskStatus;

}
