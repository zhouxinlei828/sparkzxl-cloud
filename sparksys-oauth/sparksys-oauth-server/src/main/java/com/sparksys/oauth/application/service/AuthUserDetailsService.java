package com.sparksys.oauth.application.service;

import com.sparksys.oauth.infrastructure.entity.AuthUserDetail;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final IAuthUserService authUserService;

    public AuthUserDetailsService(IAuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserDetail authUserDetail = authUserService.getAuthUserDetail(username);
        if (ObjectUtils.isEmpty(authUserDetail)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return authUserDetail;
    }
}
