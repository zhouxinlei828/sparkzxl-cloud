package com.github.sparkzxl.gateway.infrastructure.filter;

import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.JwtUserInfo;
import com.github.sparkzxl.gateway.filter.AbstractJwtAuthorizationFilter;
import com.github.sparkzxl.gateway.infrastructure.handler.AuthenticationTokenHandler;
import com.github.sparkzxl.gateway.infrastructure.properties.ResourceProperties;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * description: 权限过滤器
 *
 * @author: zhouxinlei
 * @date: 2021-01-30 22:52:36
 */
@Component
@Slf4j
@RefreshScope
public class AuthTokenFilter extends AbstractJwtAuthorizationFilter {

    @Autowired
    private ResourceProperties resourceProperties;

    @Autowired
    private AuthenticationTokenHandler authenticationTokenHandler;

    @Override
    public String getHeaderKey() {
        return BaseContextConstants.JWT_TOKEN_HEADER;
    }

    @Override
    public List<String> ignorePatterns() {
        if (ObjectUtils.isNotEmpty(resourceProperties.getIgnorePatterns())) {
            return Arrays.asList(resourceProperties.getIgnorePatterns());
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public JwtUserInfo<String> getJwtUserInfo(String token) {
        return authenticationTokenHandler.buildJwtUserInfo(token);
    }

    @Override
    protected Mono<Void> handleTokenEmpty(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        return chain.filter(exchange);
    }
}
