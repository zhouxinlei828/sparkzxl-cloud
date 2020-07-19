package com.sparksys.activiti.domain.service;

import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ActRuTaskStep;
import com.sparksys.activiti.infrastructure.mapper.ActRuTaskStepMapper;
import com.sparksys.activiti.application.service.IActRuTaskStepService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 流程状态记录 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:57
 */
@Service
public class ActRuTaskStepServiceImpl extends AbstractSuperCacheServiceImpl<ActRuTaskStepMapper, ActRuTaskStep> implements IActRuTaskStepService {

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}
