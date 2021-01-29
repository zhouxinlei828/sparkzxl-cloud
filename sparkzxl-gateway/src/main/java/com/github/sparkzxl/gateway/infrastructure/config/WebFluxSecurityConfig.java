package com.github.sparkzxl.gateway.infrastructure.config;

import com.github.sparkzxl.gateway.infrastructure.components.MyServerAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Autowired
    private MyServerAuthenticationSuccessHandler myServerAuthenticationSuccessHandler;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ReactiveClientRegistrationRepository clientRegistrationRepository) {
        http.cors();
        http.oauth2Login().authenticationSuccessHandler(myServerAuthenticationSuccessHandler);
        http.logout(logout -> logout.logoutSuccessHandler(
                new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository)));
        http.logout().logoutUrl("/auth/logout");
        http.authorizeExchange().anyExchange().authenticated();
        http.oauth2ResourceServer().jwt();
        http.headers().frameOptions().disable().xssProtection().disable();
        http.csrf().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        return http.build();
    }
}
