package com.github.sparkzxl.activiti.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import com.github.sparkzxl.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 任务处理记录
 *
 * @author: zhouxinlei
 * @date: 2020-07-16 18:40:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("process_task")
@ApiModel(value = "ProcessTask对象", description = "任务处理记录")
public class ProcessTask extends Entity<Long> {

    private static final long serialVersionUID = 2422653329738199340L;

    @ApiModelProperty(value = "taskid")
    @TableField("task_id")
    private String taskId;

    @ApiModelProperty(value = "task名称")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "流程实例ID")
    @TableField("proc_inst_id")
    private String procInstId;

    @ApiModelProperty(value = "审批人")
    @TableField("approval_user")
    private String approvalUser;

    @ApiModelProperty(value = "审批结果：1 同意,2 驳回,3 结束流程")
    @TableField("approval_result")
    private Integer approvalResult;

    @ApiModelProperty(value = "审批意见")
    @TableField("approval_comment")
    private String approvalComment;

    @ApiModelProperty(value = "处理时间")
    @TableField("approval_time")
    private LocalDateTime approvalTime;

}
