package com.sparksys.authorization.domain.service;

import com.sparksys.core.constant.CacheKey;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.ICoreStationService;
import com.sparksys.authorization.infrastructure.entity.CoreStation;
import com.sparksys.authorization.infrastructure.mapper.CoreStationMapper;
import org.springframework.stereotype.Service;

/**
 * description: 岗位 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:37:46
 */
@Service
public class CoreStationServiceImpl extends AbstractSuperCacheServiceImpl<CoreStationMapper, CoreStation> implements ICoreStationService {

    @Override
    protected String getRegion() {
        return CacheKey.STATION;
    }
}
