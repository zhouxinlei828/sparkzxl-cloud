package com.github.sparkzxl.authorization.domain.service;

import com.github.sparkzxl.authorization.application.service.IAuthUserService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authorization.infrastructure.security.AuthUserDetail;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.core.utils.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description: 获取授权用户 服务实现类
 *
 * @author charles.zhou
 * @date 2020-08-03 17:16:17
 */
@Service("oauthUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private IAuthUserService authUserService;

    @Autowired
    public void setAuthUserService(IAuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAuthUserDetail(username);
    }

    public AuthUserDetail<Long> getAuthUserDetail(String username) {
        AuthUser authUser = authUserService.getByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            AuthUserInfo<Long> authUserInfo = authUserService.getAuthUserInfo(username);
            AuthUserDetail<Long> authUserDetail = new AuthUserDetail<>(authUser.getAccount(),
                    authUser.getPassword(),
                    AuthorityUtils.createAuthorityList(ListUtils.listToArray(authUserInfo.getAuthorityList())));
            authUserDetail.setId(authUser.getId());
            authUserDetail.setName(authUser.getName());
            authUserDetail.setTenant(authUser.getTenantCode());
            BaseContextHandler.setTenant(authUser.getTenantCode());
            return authUserDetail;
        }
        return null;
    }
}
