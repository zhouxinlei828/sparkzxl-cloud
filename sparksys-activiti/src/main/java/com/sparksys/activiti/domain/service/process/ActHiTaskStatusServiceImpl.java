package com.sparksys.activiti.domain.service.process;

import com.sparksys.activiti.application.service.process.IActHiTaskStatusService;
import com.sparksys.activiti.domain.repository.IActHiTaskStatusRepository;
import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ActHiTaskStatus;
import com.sparksys.activiti.infrastructure.mapper.ActHiTaskStatusMapper;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 任务历史状态记录 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:57
 */
@Service
public class ActHiTaskStatusServiceImpl extends AbstractSuperCacheServiceImpl<ActHiTaskStatusMapper, ActHiTaskStatus> implements IActHiTaskStatusService {

    @Autowired
    private IActHiTaskStatusRepository actHiTaskStatusRepository;


    @Override
    public List<ActHiTaskStatus> getProcessHistory(String processInstanceId) {
        return actHiTaskStatusRepository.getHiTaskStatus(processInstanceId);
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}