package com.sparksys.activiti.application.service;

import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.infrastructure.entity.ActivitiTask;
import com.sparksys.activiti.infrastructure.entity.ProcessDataset;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sparksys.database.dto.PageDTO;
import com.sparksys.database.service.SuperCacheService;

import javax.servlet.http.HttpServletRequest;

/**
 * description: 数据集 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:34:13
 */
public interface IProcessDatasetService extends SuperCacheService<ProcessDataset> {

    /**
     * 获取用户需要处理的任务
     *
     * @param pageDTO  分页
     * @param assignee 用户id
     * @return PageInfo<ActivitiTask>
     */
    PageInfo<ActivitiTask> getUserTask(PageDTO pageDTO, String assignee);
}
