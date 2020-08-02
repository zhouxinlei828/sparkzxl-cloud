package com.sparksys.gateway.infrastructure.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.nimbusds.jose.JWSObject;
import com.sparksys.core.constant.BaseContextConstants;
import com.sparksys.core.support.ResponseResultStatus;
import com.sparksys.core.base.api.result.ApiResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.sparksys.oauth.resource.properties.ResourceProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
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
    private ResourceProperties resourceProperties;

    @Override
    public int getOrder() {
        return -1000;
    }

    protected String getHeader(String headerName, ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String token = StrUtil.EMPTY;
        if (headers.isEmpty()) {
            return token;
        }

        List<String> headerList = headers.get(headerName);
        if (headerList == null || headerList.isEmpty()) {
            return token;
        }
        token = headerList.get(0);

        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        if (queryParams.isEmpty()) {
            return token;
        }
        return queryParams.getFirst(headerName);
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String header = getHeader(BaseContextConstants.JWT_TOKEN_HEADER, request);
        if (header.startsWith("Basic")) {
            return chain.filter(exchange);
        }
        String accessToken = StringUtils.removeStart(header, BaseContextConstants.JWT_TOKEN_HEAD);
        if (StringUtils.isBlank(accessToken)) {
            return chain.filter(exchange);
        }
        JWSObject jwsObject = JWSObject.parse(accessToken);
        String userStr = jwsObject.getPayload().toString();
        log.info("AuthTokenFilter.filter() user:{}", userStr);
        request.mutate().header("user", userStr).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    protected Mono<Void> errorResponse(ServerHttpResponse response) {
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        byte[] bytes = JSON.toJSONString(ApiResult.apiResult(ResponseResultStatus.UN_AUTHORIZED)).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }
}
