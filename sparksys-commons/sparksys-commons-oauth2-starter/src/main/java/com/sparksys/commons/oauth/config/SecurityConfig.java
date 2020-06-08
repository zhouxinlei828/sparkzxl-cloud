package com.sparksys.commons.oauth.config;

import com.sparksys.commons.core.utils.collection.ListUtils;
import com.sparksys.commons.oauth.props.IgnoreUrlsProperties;
import com.sparksys.commons.oauth.registry.SecurityRegistry;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * description: SpringSecurity配置
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:24:51
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @RefreshScope
    public IgnoreUrlsProperties ignoreUrlsProperties() {
        return new IgnoreUrlsProperties();
    }

    @Bean
    public SecurityRegistry securityRegistry() {
        return new SecurityRegistry(ignoreUrlsProperties());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 创建允许在URL中使用斜线的自定义防火墙
     *
     * @param
     * @return HttpFirewall
     */
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        SecurityRegistry securityRegistry = securityRegistry();
        for (String url : securityRegistry.getExcludePatterns()) {
            registry.antMatchers(url).permitAll();
        }
        httpSecurity.csrf()
                .disable()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .authenticated()
                .and().formLogin().permitAll();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        String[] excludeStaticPatterns = ListUtils.stringToArray(SecurityRegistry.excludeStaticPatterns);
        web.ignoring().antMatchers(excludeStaticPatterns);
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }
}
