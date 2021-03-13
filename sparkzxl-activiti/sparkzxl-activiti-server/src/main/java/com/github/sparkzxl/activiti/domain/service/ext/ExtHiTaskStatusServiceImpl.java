package com.github.sparkzxl.activiti.domain.service.ext;

import com.github.sparkzxl.activiti.application.service.ext.IExtHiTaskStatusService;
import com.github.sparkzxl.activiti.domain.repository.IExtHiTaskStatusRepository;
import com.github.sparkzxl.activiti.infrastructure.constant.ActivitiCache;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtHiTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.mapper.ExtHiTaskStatusMapper;
import com.github.sparkzxl.database.base.service.impl.SuperCacheServiceImpl;
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
public class ExtHiTaskStatusServiceImpl extends SuperCacheServiceImpl<ExtHiTaskStatusMapper, ExtHiTaskStatus> implements IExtHiTaskStatusService {

    @Autowired
    private IExtHiTaskStatusRepository actHiTaskStatusRepository;


    @Override
    public ExtHiTaskStatus getExtHiTaskStatus(String processInstanceId) {
        return actHiTaskStatusRepository.getExtHiTaskStatus(processInstanceId);
    }

    @Override
    public List<ExtHiTaskStatus> getProcessHistory(String processInstanceId) {
        return actHiTaskStatusRepository.getHiTaskStatus(processInstanceId);
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}
