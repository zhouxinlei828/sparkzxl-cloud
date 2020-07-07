package com.sparksys.oauth.application.service;

import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.cache.CacheProviderService;
import com.sparksys.commons.web.service.AbstractAuthUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * description：获取用户信息service
 *
 * @author： zhouxinlei
 * @date： 2020-06-22 12:57:50
 */
@Service
@Slf4j
public class AuthUserSecurityService extends AbstractAuthUserRequest {

    private final CacheProviderService cacheProviderService;

    public AuthUserSecurityService(@Qualifier("redisCacheProvider") CacheProviderService cacheProviderService) {
        this.cacheProviderService = cacheProviderService;
    }


    @Override
    protected GlobalAuthUser getCache(String key) {
        return cacheProviderService.get(key);
    }
}