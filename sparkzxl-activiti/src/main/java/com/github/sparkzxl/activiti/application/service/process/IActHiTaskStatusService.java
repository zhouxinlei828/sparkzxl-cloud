package com.github.sparkzxl.activiti.application.service.process;

import com.github.sparkzxl.activiti.infrastructure.entity.ActHiTaskStatus;
import com.github.sparkzxl.database.base.service.SuperCacheService;

import java.util.List;

/**
 * description: 任务历史状态记录 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-23 14:40:51
 */
public interface IActHiTaskStatusService extends SuperCacheService<ActHiTaskStatus> {
    /**
     * 查询任务历史
     *
     * @param processInstanceId 流程实例id
     * @return List<ProcessHistoryDTO>
     */
    List<ActHiTaskStatus> getProcessHistory(String processInstanceId);
}
