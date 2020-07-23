package com.sparksys.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.activiti.domain.repository.IActHiTaskStatusRepository;
import com.sparksys.activiti.infrastructure.entity.ActHiTaskStatus;
import com.sparksys.activiti.infrastructure.mapper.ActHiTaskStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 历史流程记录 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-23 17:13:52
 */
@Repository
public class ActHiTaskStatusRepositoryImpl implements IActHiTaskStatusRepository {

    @Autowired
    private ActHiTaskStatusMapper actHiTaskStatusMapper;

    @Override
    public List<ActHiTaskStatus> getHiTaskStatus(String processInstanceId) {
        return actHiTaskStatusMapper.selectList(new QueryWrapper<ActHiTaskStatus>().lambda().eq(ActHiTaskStatus::getProcessInstanceId,
                processInstanceId).orderByAsc(ActHiTaskStatus::getTaskId));
    }
}
