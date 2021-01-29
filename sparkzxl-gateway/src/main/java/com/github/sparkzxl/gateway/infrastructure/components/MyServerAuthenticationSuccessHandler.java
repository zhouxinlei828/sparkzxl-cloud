package com.github.sparkzxl.gateway.infrastructure.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * description: 授权重定向处理
 *
 * @author: zhouxinlei
 * @date: 2021-01-29 10:15:07
 */
@Component
public class MyServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private final ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

    @Value("${application.frontend_url}")
    private String defaultLoginSuccessUrl;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        URI url = URI.create(defaultLoginSuccessUrl);
        return this.redirectStrategy
                .sendRedirect(webFilterExchange.getExchange(), url);
    }

}
