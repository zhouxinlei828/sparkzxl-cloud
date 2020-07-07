package com.sparksys.oauth.domain.service;

import com.sparksys.commons.core.cache.CacheKey;
import com.sparksys.commons.mybatis.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.application.service.IAuthResourceService;
import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.oauth.infrastructure.mapper.AuthResourceMapper;
import org.springframework.stereotype.Service;

/**
 * description: 资源 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:36:15
 */
@Service
public class AuthResourceServiceImpl extends AbstractSuperCacheServiceImpl<AuthResourceMapper, AuthResource> implements IAuthResourceService {

    @Override
    protected String getRegion() {
        return CacheKey.RESOURCE;
    }
}
