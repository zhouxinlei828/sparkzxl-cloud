package com.sparksys.authorization.infrastructure.config;

import com.sparksys.authorization.domain.service.AuthUserDetailsService;
import com.sparksys.commons.security.service.AbstractAuthSecurityService;
import com.sparksys.commons.web.component.AuthUserArgumentResolver;
import com.sparksys.commons.web.config.GlobalWebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * description: WebConfig全局配置
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:25:18
 */
@Configuration
@ConditionalOnClass(GlobalWebConfig.class)
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AbstractAuthSecurityService abstractSecurityAuthDetailService;

    @Bean
    public AuthUserArgumentResolver authUserArgumentResolver() {
        return new AuthUserArgumentResolver(abstractSecurityAuthDetailService);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authUserArgumentResolver());
    }

}
