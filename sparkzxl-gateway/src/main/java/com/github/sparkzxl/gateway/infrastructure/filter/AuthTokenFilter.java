package com.github.sparkzxl.gateway.infrastructure.filter;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.URLUtil;
import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.jwt.entity.JwtUserInfo;
import com.github.sparkzxl.jwt.service.JwtTokenService;
import com.github.sparkzxl.oauth.utils.WebFluxUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description: 权限过滤器
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:16:11
 */
@Component
@Slf4j
@RefreshScope
public class AuthTokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtTokenService<Long> jwtTokenService;

    @Override
    public int getOrder() {
        return -1000;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String header = WebFluxUtils.getHeader(BaseContextConstants.JWT_TOKEN_HEADER, request);
        if (StringUtils.isNotEmpty(header)) {
            if (header.startsWith(BaseContextConstants.BASIC_AUTH)) {
                return chain.filter(exchange);
            }
            String accessToken = StringUtils.removeStartIgnoreCase(header, BaseContextConstants.BEARER_TOKEN);
            if (StringUtils.isBlank(accessToken)) {
                return chain.filter(exchange);
            }
            JwtUserInfo<Long> jwtUserInfo = null;
            try {
                jwtUserInfo = jwtTokenService.getJwtUserInfo(accessToken);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("jwt 获取用户发生异常：{}", ExceptionUtil.getMessage(e));
            }
            if (jwtUserInfo != null) {
                addHeader(mutate, BaseContextConstants.JWT_KEY_ACCOUNT, jwtUserInfo.getUsername());
                addHeader(mutate, BaseContextConstants.JWT_KEY_USER_ID, jwtUserInfo.getId());
                addHeader(mutate, BaseContextConstants.JWT_KEY_NAME, jwtUserInfo.getName());
                MDC.put(BaseContextConstants.JWT_KEY_USER_ID, String.valueOf(jwtUserInfo.getId()));
            }
            ServerHttpRequest build = mutate.build();
            exchange = exchange.mutate().request(build).build();
        }
        return chain.filter(exchange);
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (ObjectUtil.isEmpty(value)) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = URLUtil.encode(valueStr);
        mutate.header(name, valueEncode);
    }

}
