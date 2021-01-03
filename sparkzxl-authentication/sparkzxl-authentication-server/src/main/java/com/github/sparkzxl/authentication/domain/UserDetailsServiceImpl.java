package com.github.sparkzxl.authentication.domain;

import com.github.sparkzxl.authority.application.service.IAuthUserService;
import com.github.sparkzxl.authority.infrastructure.entity.AuthUser;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.security.entity.AuthUserDetail;
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

    public AuthUserDetail<Long> getAuthUserDetail(String username){
        AuthUser authUser = authUserService.getByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            AuthUserInfo<Long> authUserInfo = authUserService.getAuthUserInfo(username);
            return new AuthUserDetail<>(authUser.getId(),
                    authUser.getAccount(),
                    authUser.getPassword(),
                    authUser.getName(),
                    authUser.getStatus(),
                    authUserInfo.getAuthorityList());
        }
        return null;
    }
}
