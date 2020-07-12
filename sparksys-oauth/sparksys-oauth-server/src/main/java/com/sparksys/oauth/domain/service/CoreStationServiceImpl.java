package com.sparksys.oauth.domain.service;

import com.sparksys.commons.core.constant.CacheKey;
import com.sparksys.commons.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.application.service.ICoreStationService;
import com.sparksys.oauth.infrastructure.entity.CoreStation;
import com.sparksys.oauth.infrastructure.mapper.CoreStationMapper;
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
