package com.github.sparkzxl.activiti.domain.service.process;

import com.github.sparkzxl.activiti.application.service.process.IActHiTaskStatusService;
import com.github.sparkzxl.activiti.domain.repository.IActHiTaskStatusRepository;
import com.github.sparkzxl.activiti.infrastructure.constant.ActivitiCache;
import com.github.sparkzxl.activiti.infrastructure.entity.ActHiTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.mapper.ActHiTaskStatusMapper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
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
