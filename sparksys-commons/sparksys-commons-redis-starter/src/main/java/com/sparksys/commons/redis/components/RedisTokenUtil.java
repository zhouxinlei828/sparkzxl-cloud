package com.sparksys.commons.redis.components;

import com.sparksys.commons.redis.cache.CacheProviderService;
import com.sparksys.commons.redis.util.SnowFlakeId;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * description: redis token生成组件
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:29:13
 */
@Component
public class RedisTokenUtil {

    private final CacheProviderService cacheProviderService;

    public RedisTokenUtil(CacheProviderService cacheProviderService) {
        this.cacheProviderService = cacheProviderService;
    }

    public String getToken() {
        Long tokenId = SnowFlakeId.getSnowFlakeId();
        String token = "token".concat(tokenId.toString());
        long expire = 60 * 60;
        cacheProviderService.set(token, token, expire);
        return token;
    }


    public boolean findToken(String token) {
        String value = cacheProviderService.get(token);
        if (!StringUtils.isEmpty(value)) {
            cacheProviderService.remove(token);
            return true;
        }
        return false;
    }
}
