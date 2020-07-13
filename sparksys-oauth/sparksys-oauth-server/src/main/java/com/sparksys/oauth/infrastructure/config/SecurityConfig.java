package com.sparksys.oauth.infrastructure.config;

import com.sparksys.commons.security.config.AbstractSecurityConfig;
import com.sparksys.commons.security.properties.IgnoreUrlsProperties;
import com.sparksys.commons.security.registry.SecurityRegistry;
import com.sparksys.oauth.application.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * description: SpringSecurity配置
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:24:51
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends AbstractSecurityConfig {


    @Autowired
    private IAuthUserService authUserService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> authUserService.getAuthUserDetail(username);
    }


    @Bean
    @Override
    protected SecurityRegistry securityRegistry() {
        return new SecurityRegistry(ignoreUrlsProperties());
    }

    @Override
    @Bean
    @RefreshScope
    public IgnoreUrlsProperties ignoreUrlsProperties() {
        return new IgnoreUrlsProperties();
    }
}
