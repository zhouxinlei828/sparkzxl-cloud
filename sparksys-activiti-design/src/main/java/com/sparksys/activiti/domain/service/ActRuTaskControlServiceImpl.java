package com.sparksys.activiti.domain.service;

import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ActRuTaskControl;
import com.sparksys.activiti.infrastructure.mapper.ActRuTaskControlMapper;
import com.sparksys.activiti.application.service.IActRuTaskControlService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description:流程控制 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:00
 */
@Service
public class ActRuTaskControlServiceImpl extends AbstractSuperCacheServiceImpl<ActRuTaskControlMapper, ActRuTaskControl> implements IActRuTaskControlService {

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_CONTROL;
    }
}
