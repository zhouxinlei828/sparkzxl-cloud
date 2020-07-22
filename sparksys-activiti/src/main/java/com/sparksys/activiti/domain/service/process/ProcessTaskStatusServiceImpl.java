package com.sparksys.activiti.domain.service.process;

import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskStatus;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskStatusMapper;
import com.sparksys.activiti.application.service.process.IProcessTaskStatusService;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 流程历史状态记录 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:57
 */
@Service
public class ProcessTaskStatusServiceImpl extends AbstractSuperCacheServiceImpl<ProcessTaskStatusMapper, ProcessTaskStatus> implements IProcessTaskStatusService {

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}
