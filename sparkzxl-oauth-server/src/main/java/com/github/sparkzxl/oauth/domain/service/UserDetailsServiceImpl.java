package com.github.sparkzxl.oauth.domain.service;

import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.oauth.application.service.IAuthUserService;
import com.github.sparkzxl.oauth.entity.AuthUserDetail;
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

    private final IAuthUserService authUserService;

    public UserDetailsServiceImpl(IAuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserDetail authUserDetail = authUserService.getAuthUserDetail(username);
        ResponseResultStatus.ACCOUNT_EMPTY.assertNotNull(authUserDetail);
        return authUserDetail;
    }
}
