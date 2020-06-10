package com.sparksys.authorization.infrastructure.config;

import com.sparksys.commons.security.service.AbstractSecurityAuthDetailService;
import com.sparksys.commons.web.component.AuthUserArgumentResolver;
import com.sparksys.commons.web.config.GlobalWebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

/**
 * description: WebConfig全局配置
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:25:18
 */
@Configuration
public class WebConfig extends GlobalWebConfig {

    @Autowired
    AbstractSecurityAuthDetailService abstractSecurityAuthDetailService;

    @Bean
    public AuthUserArgumentResolver authUserArgumentResolver() {
        return new AuthUserArgumentResolver(abstractSecurityAuthDetailService);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authUserArgumentResolver());
    }

}
