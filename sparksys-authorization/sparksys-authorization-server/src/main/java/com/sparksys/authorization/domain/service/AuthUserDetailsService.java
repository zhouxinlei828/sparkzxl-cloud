package com.sparksys.authorization.domain.service;

import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.support.ResponseResultStatus;

import com.sparksys.commons.security.entity.AuthUserDetail;
import com.sparksys.commons.security.service.AbstractAuthSecurityService;
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


    @Override
    public AuthUserDetail getAuthUserDetail(String account) {
        AuthUserDetail authUserDetail = authUserService.getAuthUserDetail(account);
        ResponseResultStatus.ACCOUNT_EMPTY.assertNotNull(authUserDetail);
        return authUserDetail;
    }
}
