package com.github.sparkzxl.gateway.infrastructure.handler;

import cn.hutool.core.date.DateUnit;
import cn.hutool.json.JSONUtil;
import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.core.entity.JwtUserInfo;
import com.github.sparkzxl.core.jackson.JsonUtil;
import com.github.sparkzxl.core.utils.BuildKeyUtils;
import com.github.sparkzxl.core.utils.DateUtils;
import com.nimbusds.jose.JWSObject;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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

    public <T> AuthUserInfo<T> buildAuthUserInfoCache(String accessToken, Instant expiresAt) {
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(accessToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String payload = jwsObject.getPayload().toString();
        JwtUserInfo<T> jwtUserInfo = JSONUtil.toBean(payload, JwtUserInfo.class);
        String username = jwtUserInfo.getPreferredUsername();
        String name = jwtUserInfo.getFamilyName().concat(jwtUserInfo.getGivenName());
        String sub = jwtUserInfo.getSub();
        jwtUserInfo.setId((T) sub);
        jwtUserInfo.setUsername(username);
        jwtUserInfo.setName(name);
        AuthUserInfo<T> authUserInfo = new AuthUserInfo();
        authUserInfo.setId((T) sub);
        authUserInfo.setAccount(username);
        authUserInfo.setName(name);
        authUserInfo.setStatus(true);
        if (ObjectUtils.isNotEmpty(jwtUserInfo.getResourceAccess())
                && ObjectUtils.isNotEmpty(jwtUserInfo.getResourceAccess().getAccount())
                && ObjectUtils.isNotEmpty(jwtUserInfo.getResourceAccess().getAccount().getRoles())) {
            List<String> roles = jwtUserInfo.getResourceAccess().getAccount().getRoles();
            authUserInfo.setRoleList(roles);
        }
        Map extraInfo = JsonUtil.toMap(JSONUtil.toJsonStr(jwtUserInfo));
        authUserInfo.setExtraInfo(extraInfo);
        String authUserInfoKey = BuildKeyUtils.generateKey(BaseContextConstants.AUTH_USER_TOKEN, authUserInfo.getId());
        Date date = DateUtils.date(expiresAt);
        long expire = DateUtils.between(new Date(), date, DateUnit.SECOND, false);
        redisTemplate.opsForHash().put(authUserInfoKey, accessToken, authUserInfo);
        redisTemplate.expire(authUserInfoKey, expire, TimeUnit.SECONDS);
        return authUserInfo;
    }

}
