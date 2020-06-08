package com.sparksys.authorization.infrastructure.config;

import com.sparksys.commons.security.config.AbstractSecurityConfig;
import com.sparksys.commons.security.props.IgnoreUrlsProperties;
import com.sparksys.commons.security.registry.SecurityRegistry;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * description：SecurityConfig配置
 *
 * @Author zhouxinlei
 * @Date 2020/6/6 10:28 上午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends AbstractSecurityConfig {

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
