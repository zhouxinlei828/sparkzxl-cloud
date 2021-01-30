package com.github.sparkzxl.gateway.infrastructure.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sparkzxl.core.jackson.JsonUtil;
import com.github.sparkzxl.gateway.infrastructure.handler.AuthenticationTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;

/**
 * description: 授权重定向处理
 *
 * @author: zhouxinlei
 * @date: 2021-01-29 10:15:07
 */
@Component
public class MyServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private final ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

    @Autowired
    private AuthenticationTokenHandler authenticationTokenHandler;


    @Value("${application.frontend_url}")
    private String defaultLoginSuccessUrl;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        JsonNode jsonNode = JsonUtil.readTree(JsonUtil.toJson(principal));
        JsonNode idTokenNode = jsonNode.get("idToken");
        String tokenValue = idTokenNode.get("tokenValue").asText();
        JsonNode expiresAtNode = idTokenNode.get("expiresAt");
        Instant expiresAt = JsonUtil.toPojo(expiresAtNode, Instant.class);
        authenticationTokenHandler.buildAuthUserInfoCache(tokenValue, expiresAt);
        URI url = URI.create(defaultLoginSuccessUrl);
        return this.redirectStrategy
                .sendRedirect(webFilterExchange.getExchange(), url);
    }
}
