package com.sparksys.authorization.domain.service;

import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.cache.template.CacheTemplate;
import com.sparksys.core.constant.BaseContextConstants;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.core.support.ResponseResultStatus;
import com.sparksys.core.utils.KeyUtils;
import com.sparksys.security.entity.AuthToken;
import com.sparksys.security.entity.AuthUserDetail;
import com.sparksys.security.service.AbstractAuthSecurityService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

/**
 * description: security 加载用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:23:23
 */
@Service
public class AuthUserDetailsService extends AbstractAuthSecurityService {

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private CacheTemplate cacheTemplate;

    @Override
    public AuthUserDetail getAuthUserDetail(String account) {
        AuthUserDetail authUserDetail = authUserService.getAuthUserDetail(account);
        ResponseResultStatus.ACCOUNT_EMPTY.assertNotNull(authUserDetail);
        return authUserDetail;
    }

    @Override
    public void accessToken(AuthToken authToken, AuthUserInfo authUser) {
        String token = authToken.getToken();
        cacheTemplate.set(KeyUtils.buildKey(BaseContextConstants.AUTH_USER, token), authUser,
                authToken.getExpiration());
    }
}
