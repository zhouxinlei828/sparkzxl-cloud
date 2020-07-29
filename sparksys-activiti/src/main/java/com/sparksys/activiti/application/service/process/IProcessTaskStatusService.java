package com.sparksys.activiti.application.service.process;

import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.infrastructure.entity.ProcessInstance;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskStatus;
import com.sparksys.activiti.interfaces.dto.act.InstancePageDTO;
import com.sparksys.activiti.interfaces.dto.act.ProcessInstanceDTO;
import com.sparksys.database.base.service.SuperCacheService;

import java.util.List;

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
     * @param processInstanceId 流程实例id
     * @return ProcessTaskStatus
     */
    ProcessTaskStatus getProcessTaskStatus(String processInstanceId);

    /**
     * 查询流程实例列表
     *
     * @param instancePageDTO 流程实例查询入参
     * @return PageInfo<ProcessInstanceDTO>
     */
    PageInfo<ProcessInstance> getProcessInstanceList(InstancePageDTO instancePageDTO);
}
