package com.github.sparkzxl.activiti.application.service.process;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskStatus;
import com.github.sparkzxl.activiti.interfaces.dto.act.InstancePageDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;

/**
 * description: 流程状态记录 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:21:47
 */
public interface IProcessTaskStatusService extends SuperCacheService<ProcessTaskStatus> {

    /**
     * 获取流程状态
     *
     * @param businessId 业务主键
     * @return ProcessTaskStatus
     */
    ProcessTaskStatus getProcessTaskStatus(String businessId);

    /**
     * 查询流程实例列表
     *
     * @param instancePageDTO 流程实例查询入参
     * @return PageInfo<ProcessInstanceDTO>
     */
    PageInfo<ProcessInstance> getProcessInstanceList(InstancePageDTO instancePageDTO);
}
