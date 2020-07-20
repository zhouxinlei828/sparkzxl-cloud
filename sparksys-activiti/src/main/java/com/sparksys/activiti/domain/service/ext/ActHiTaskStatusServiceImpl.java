package com.sparksys.activiti.domain.service.ext;

import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ActHiTaskStatus;
import com.sparksys.activiti.infrastructure.mapper.ActHiTaskStatusMapper;
import com.sparksys.activiti.application.service.ext.IActHiTaskStatusService;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 流程历史状态记录 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:57
 */
@Service
public class ActHiTaskStatusServiceImpl extends AbstractSuperCacheServiceImpl<ActHiTaskStatusMapper, ActHiTaskStatus> implements IActHiTaskStatusService {

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}
