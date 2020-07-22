package com.sparksys.activiti.domain.entity;

import lombok.Data;

/**
 * description: 流程驱动model
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:11:51
 */
@Data
public class DriveProcess {

    /**
     * 流程定义key
     */
    private String processDefinitionKey;

    /**
     * 业务主键
     */
    protected String businessId;

    /**
     * 流程动作类型
     */
    private int actType;

    /**
     * 评论
     */
    private String comment;

    /**
     * 申请人id
     */
    private String applyUserId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 任务id
     */
    private String taskId;

}
