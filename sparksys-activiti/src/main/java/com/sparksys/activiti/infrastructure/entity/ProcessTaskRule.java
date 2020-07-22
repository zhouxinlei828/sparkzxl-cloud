package com.sparksys.activiti.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import com.sparksys.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 流程跳转控制实体类
 *
 * @author: zhouxinlei
 * @date: 2020-07-16 18:35:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("process_task_rule")
@ApiModel(value = "ProcessTaskRule对象", description = "")
public class ProcessTaskRule extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "目标任务定义key")
    @TableField("task_def_key")
    private String taskDefKey;

    @ApiModelProperty(value = "流程类型")
    @TableField("act_type")
    private Integer actType;


}
