package com.github.sparkzxl.resource.infrastructure.config;

import com.github.sparkzxl.core.resource.SwaggerStaticResource;
import com.github.sparkzxl.core.utils.ListUtils;
import com.github.sparkzxl.resource.infrastructure.component.RestAuthenticationEntryPoint;
import com.github.sparkzxl.resource.infrastructure.component.RestfulAccessDeniedHandler;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.List;

/**
 * description: 资源服务器
 *
 * @author: zhouxinlei
 * @date: 2021-02-01 11:30:00
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        RestfulAccessDeniedHandler restfulAccessDeniedHandler = new RestfulAccessDeniedHandler();
        http.logout().logoutUrl("/logout")
                .and().authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/user/test").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .and()
                .requestMatchers()
                .antMatchers("/user/**");
    }
}
