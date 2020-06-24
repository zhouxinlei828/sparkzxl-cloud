package com.sparksys.authorization.infrastructure.config;

import com.sparksys.authorization.domain.service.AuthUserDetailsService;
import com.sparksys.commons.security.config.AbstractSecurityConfig;
import com.sparksys.commons.security.props.IgnoreUrlsProperties;
import com.sparksys.commons.security.registry.SecurityRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * description：SecurityConfig配置
 *
 * @author zhouxinlei
 * @date 2020/6/6 10:28 上午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends AbstractSecurityConfig {

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> authUserDetailsService.getAuthUserDetail(username);
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
