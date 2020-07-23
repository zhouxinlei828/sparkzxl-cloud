package com.sparksys.activiti.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class TaskComment {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "任务定义key")
    private String taskDefKey;

    @ApiModelProperty(value = "备注/意见")
    private String comment;

}
