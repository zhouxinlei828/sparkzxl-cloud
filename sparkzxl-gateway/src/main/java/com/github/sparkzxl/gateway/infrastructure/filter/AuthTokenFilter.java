package com.github.sparkzxl.gateway.infrastructure.filter;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.JwtUserInfo;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.gateway.filter.AbstractJwtAuthorizationFilter;
import com.github.sparkzxl.jwt.service.JwtTokenService;
import com.github.sparkzxl.oauth.properties.ResourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * description: 权限过滤器
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:16:11
 */
@Component
@Slf4j
@RefreshScope
public class AuthTokenFilter extends AbstractJwtAuthorizationFilter {

    @Autowired
    private ResourceProperties resourceProperties;

    @Autowired
    private JwtTokenService<Long> jwtTokenService;

    @Override
    public String getHeaderKey() {
        return BaseContextConstants.BASIC_HEADER_KEY;
    }

    @Override
    public List<String> ignorePatterns() {
        return Arrays.asList(resourceProperties.getIgnorePatterns());
    }

    @Override
    public JwtUserInfo getJwtUserInfo(String token) throws Exception {
        return jwtTokenService.getJwtUserInfo(token);
    }

    @Override
    protected Mono<Void> handleTokenEmpty(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        return chain.filter(exchange);
    }
}
