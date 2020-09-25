package com.github.sparkzxl.activiti.domain.repository;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskStatus;

/**
 * description: 流程状态 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-23 13:58:34
 */
public interface IProcessTaskStatusRepository {

    /**
     * 根据业务主键查询流程状态
     *
     * @param businessId 业务主键
     * @return ProcessTaskStatus
     */
    ProcessTaskStatus getProcessTaskStatus(String businessId);

    /**
     * 查询流程实例列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     * @param name     流程名称
     * @return PageInfo<ProcessInstance>
     */
    PageInfo<ProcessInstance> getProcessInstanceList(int pageNum, int pageSize, String name);
}
