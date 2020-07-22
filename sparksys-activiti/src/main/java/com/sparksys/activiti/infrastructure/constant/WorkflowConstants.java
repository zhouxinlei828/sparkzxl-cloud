package com.sparksys.activiti.infrastructure.constant;

import java.awt.*;

/**
 * 流程常量
 */
public final class WorkflowConstants {

    /**
     * 审批结果_流程变量名
     */
    public static final String APPROVAL_RESULT_VARIABLE_NAME = "approval_result";

    /**
     * 流程图颜色定义
     */
    public static final Color COLOR_NORMAL = new Color(0, 205, 0);
    public static final Color COLOR_CURRENT = new Color(255, 0, 0);

    /**
     * 生成流程图时的边距(像素)
     */
    public static final int PROCESS_PADDING = 5;


    /**
     * 流程任务动作类型
     */
    public static class WorkflowAction {
        /**
         * 启动
         */
        public static final int START = 0;
        /**
         * 提交
         */
        public static final int SUBMIT = 1;

        /**
         * 驳回
         */
        public static final int ROLLBACK = -1;
        /**
         * 分发
         */
        public static final int JUMP = 2;
        /**
         * 退回分发
         */
        public static final int CANCEL_CLAIM = -2;
        /**
         * 流程挂起
         */
        public static final int SUSPEND = 3;
        /**
         * 直接提交至被分发者
         */
        public static final int ROUND_CLAIM = 4;
    }

}