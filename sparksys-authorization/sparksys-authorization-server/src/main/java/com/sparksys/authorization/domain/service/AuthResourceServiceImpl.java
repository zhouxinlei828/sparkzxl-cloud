package com.sparksys.authorization.domain.service;

import com.sparksys.commons.core.constant.CacheKey;
import com.sparksys.commons.mybatis.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.IAuthResourceService;
import com.sparksys.authorization.infrastructure.entity.AuthResource;
import com.sparksys.authorization.infrastructure.mapper.AuthResourceMapper;
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
