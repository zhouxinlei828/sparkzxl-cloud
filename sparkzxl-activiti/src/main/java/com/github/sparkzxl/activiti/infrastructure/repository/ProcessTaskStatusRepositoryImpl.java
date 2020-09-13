package com.github.sparkzxl.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.domain.repository.IProcessTaskStatusRepository;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.mapper.ProcessTaskStatusMapper;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * description:流程状态 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-23 13:59:16
 */
@Repository
public class ProcessTaskStatusRepositoryImpl implements IProcessTaskStatusRepository {

    @Autowired
    private ProcessTaskStatusMapper taskStatusMapper;

    @Override
    public ProcessTaskStatus getProcessTaskStatus(String processInstanceId) {
        return taskStatusMapper.selectOne(new QueryWrapper<ProcessTaskStatus>().lambda().eq(ProcessTaskStatus::getProcessInstanceId, processInstanceId));
    }

    @Override
    public PageInfo<ProcessInstance> getProcessInstanceList(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfoUtils.pageInfo(taskStatusMapper.getProcessInstanceList(name));
    }
}
