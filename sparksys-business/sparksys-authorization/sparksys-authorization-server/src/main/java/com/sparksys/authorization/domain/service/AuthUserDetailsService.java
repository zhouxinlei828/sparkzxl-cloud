package com.sparksys.authorization.domain.service;

import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.core.support.ResponseResultStatus;
import com.sparksys.commons.redis.cache.CacheProviderService;
import com.sparksys.commons.security.entity.AdminUserDetails;
import com.sparksys.commons.security.service.AbstractSecurityAuthDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: security 加载用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:23:23
 */
@Service
public class AuthUserDetailsService extends AbstractSecurityAuthDetailService {

    @Autowired
    private IAuthUserService authUserService;
    @Autowired
    private CacheProviderService cacheProviderService;

    @Override
    public List<String> listOauthPermission(Long adminId) {
        return null;
    }


    @Override
    protected AuthUser getCache(String key) {
        return cacheProviderService.get(key);
    }

    @Override
    public AdminUserDetails getAdminUserDetail(String account) {
        AdminUserDetails authUserDetail = authUserService.getAdminUserDetails(account);
        ResponseResultStatus.ACCOUNT_EMPTY.assertNotNull(authUserDetail);
        return authUserDetail;
    }
}
