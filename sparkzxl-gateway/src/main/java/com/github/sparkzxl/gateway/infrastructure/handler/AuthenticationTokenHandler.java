package com.github.sparkzxl.gateway.infrastructure.handler;

import cn.hutool.core.date.DateUnit;
import cn.hutool.json.JSONUtil;
import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.core.entity.JwtUserInfo;
import com.github.sparkzxl.core.jackson.JsonUtil;
import com.github.sparkzxl.core.utils.BuildKeyUtils;
import com.github.sparkzxl.core.utils.DateUtils;
import com.github.sparkzxl.core.utils.ReflectObjectUtils;
import com.google.common.collect.Maps;
import com.nimbusds.jose.JWSObject;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * description: 鉴权处理类
 *
 * @author: zhouxinlei
 * @date: 2021-01-30 13:35:47
 */
@Component
public class AuthenticationTokenHandler {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public JwtUserInfo<String> buildJwtUserInfo(String accessToken) {
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(accessToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String payload = jwsObject.getPayload().toString();
        JwtUserInfo<String> jwtUserInfo = JSONUtil.toBean(payload, JwtUserInfo.class);
        String username = jwtUserInfo.getPreferredUsername();
        String name = jwtUserInfo.getFamilyName().concat(jwtUserInfo.getGivenName());
        String sub = jwtUserInfo.getSub();
        jwtUserInfo.setId(sub);
        jwtUserInfo.setUsername(username);
        jwtUserInfo.setName(name);
        return jwtUserInfo;
    }

    public AuthUserInfo<String> buildAuthUserInfoCache(String accessToken, Instant expiresAt) {
        JwtUserInfo<String> jwtUserInfo = buildJwtUserInfo(accessToken);
        AuthUserInfo<String> authUserInfo = new AuthUserInfo<>();
        authUserInfo.setId(jwtUserInfo.getId());
        authUserInfo.setAccount(jwtUserInfo.getUsername());
        authUserInfo.setName(jwtUserInfo.getName());
        authUserInfo.setStatus(true);
        if (ObjectUtils.isNotEmpty(jwtUserInfo.getResourceAccess())
                && ObjectUtils.isNotEmpty(jwtUserInfo.getResourceAccess().getAccount())
                && ObjectUtils.isNotEmpty(jwtUserInfo.getResourceAccess().getAccount().getRoles())) {
            List<String> roles = jwtUserInfo.getResourceAccess().getAccount().getRoles();
            authUserInfo.setRoleList(roles);
        }
        Map<String, Object> extraInfo = ReflectObjectUtils.getKeyAndValue(jwtUserInfo);
        authUserInfo.setExtraInfo(extraInfo);
        String authUserInfoKey = BuildKeyUtils.generateKey(BaseContextConstants.AUTH_USER_TOKEN, authUserInfo.getId());
        Date date = DateUtils.date(expiresAt);
        long expire = DateUtils.between(new Date(), date, DateUnit.SECOND, false);
        redisTemplate.opsForHash().put(authUserInfoKey, accessToken, authUserInfo);
        redisTemplate.expire(authUserInfoKey, expire, TimeUnit.SECONDS);
        return authUserInfo;
    }


    public ServerWebExchange withBearerAuth(ServerWebExchange exchange, String accessToken, Instant expiresAt) {
        AuthUserInfo<String> authUserInfo = buildAuthUserInfoCache(accessToken,
                expiresAt);
        String username = authUserInfo.getName();
        String account = authUserInfo.getAccount();
        String sub = authUserInfo.getId();
        MDC.put(BaseContextConstants.JWT_KEY_USER_ID, sub);
        return exchange.mutate().request(r -> r.header(BaseContextConstants.JWT_TOKEN_HEADER, accessToken)
                .header(BaseContextConstants.JWT_KEY_USER_ID, sub)
                .header(BaseContextConstants.KEYCLOAK_KEY, BaseContextConstants.KEYCLOAK_KEY)
                .header(BaseContextConstants.JWT_KEY_ACCOUNT, account)
                .header(BaseContextConstants.JWT_KEY_NAME, username)).build();
    }


}
