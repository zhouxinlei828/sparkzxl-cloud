package com.sparksys.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.domain.repository.IProcessTaskStatusRepository;
import com.sparksys.activiti.infrastructure.entity.ProcessInstance;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskStatus;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskStatusMapper;
import com.sparksys.database.utils.PageInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
