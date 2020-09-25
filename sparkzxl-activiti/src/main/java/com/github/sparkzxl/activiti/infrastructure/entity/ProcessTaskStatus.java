package com.github.sparkzxl.activiti.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import com.github.sparkzxl.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 流程状态记录（新加表）
 *
 * @author: zhouxinlei
 * @date: 2020-07-16 18:39:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("process_task_status")
@ApiModel(value = "ActHiTaskStatus对象", description = "")
public class ProcessTaskStatus extends Entity<Long> {

    private static final long serialVersionUID = -8616461939623484360L;

    @ApiModelProperty(value = "业务主键")
    @TableField("business_id")
    private String businessId;

    @ApiModelProperty(value = "流程id")
    @TableField("process_instance_id")
    private String processInstanceId;

    @ApiModelProperty(value = "流程状态")
    @TableField("process_status")
    private String processStatus;

}
