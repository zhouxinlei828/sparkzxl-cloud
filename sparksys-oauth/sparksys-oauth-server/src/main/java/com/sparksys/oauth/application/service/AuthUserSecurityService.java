package com.sparksys.oauth.application.service;

import com.sparksys.commons.core.repository.CacheRepository;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.service.AbstractAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * description：获取用户信息service
 *
 * @author： zhouxinlei
 * @date： 2020-06-22 12:57:50
 */
@Service
@Slf4j
public class AuthUserSecurityService extends AbstractAuthService {

    @Autowired
    private CacheRepository cacheRepository;


    @Override
    protected GlobalAuthUser getCache(String key) {
        return cacheRepository.get(key);
    }
}