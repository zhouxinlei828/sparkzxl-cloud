package com.github.sparkzxl.authorization.domain.service;

import com.github.sparkzxl.authorization.application.service.IAuthUserService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authorization.infrastructure.security.AuthUserDetail;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description: 获取授权用户 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-08-03 17:16:17
 */
@Service("oauthUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAuthUserService authUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAuthUserDetail(username);
    }

    public AuthUserDetail<Long> getAuthUserDetail(String username) {
        AuthUser authUser = authUserService.getByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            AuthUserInfo<Long> authUserInfo = authUserService.getAuthUserInfo(username);
            AuthUserDetail<Long> authUserDetail = new AuthUserDetail<>(authUser.getId(),
                    authUser.getAccount(),
                    authUser.getPassword(),
                    authUser.getName(),
                    authUser.getStatus(),
                    authUserInfo.getAuthorityList());
            authUserDetail.setTenant(authUser.getTenantCode());
            BaseContextHandler.setTenant(authUser.getTenantCode());
            return authUserDetail;
        }
        return null;
    }
}
