package com.sparksys.oauth.application.service;

import com.sparksys.commons.cache.service.CacheProviderImpl;
import com.sparksys.commons.cache.service.RedisCacheAdapter;
import com.sparksys.commons.core.cache.JdkCacheProxy;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.cache.CacheProviderService;
import com.sparksys.commons.web.component.SpringContextUtils;
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


    @Override
    protected GlobalAuthUser getCache(String key) {
        CacheProviderService cacheProviderService = JdkCacheProxy.getProxy(CacheProviderImpl.class,
                SpringContextUtils.getBean(RedisCacheAdapter.class));
        return cacheProviderService.get(key);
    }
}