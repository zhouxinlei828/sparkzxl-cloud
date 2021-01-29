package com.github.sparkzxl.gateway.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**")
                .hasAuthority("SCOPE_openid")
                // .antMatchers("/**")
                // .hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable().xssProtection().disable()
                .and()
                .formLogin().disable()
                .oauth2ResourceServer()
                .jwt();
    }

}
