package com.sparksys.commons.cache.service;

import com.sparksys.commons.core.cache.ICacheAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * description: redis缓存实现
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:28:55
 */
@Component
@Slf4j
public class RedisCacheAdapter implements ICacheAdapter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOperations;

    private final static long CACHE_MINUTE = 60;

    @PostConstruct
    public void initRedisOperation() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public <T> T get(String key) {
        return get(key, null, null, CACHE_MINUTE);
    }

    @Override
    public <T> T get(String key, Function<String, T> function) {
        return get(key, function, key, CACHE_MINUTE);
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParam) {
        return get(key, function, funcParam, CACHE_MINUTE);
    }

    @Override
    public <T> T get(String key, Function<String, T> function, Long expireTime) {
        return get(key, function, key, expireTime);
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParm, Long expireTime) {
        T obj = null;
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        expireTime = getExpireTime(expireTime);
        try {
            obj = (T) valueOperations.get(key);
            if (function != null && obj == null) {
                obj = function.apply(funcParm);
                if (obj != null) {
                    //设置缓存信息
                    set(key, obj, expireTime);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return obj;
    }

    @Override
    public <T> void set(String key, T obj) {
        set(key, obj, CACHE_MINUTE);
    }

    @Override
    public <T> void set(String key, T obj, Long expireTime) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (obj == null) {
            return;
        }
        expireTime = getExpireTime(expireTime);
        valueOperations.set(key, obj);
        expire(key, expireTime);
    }

    @Override
    public void expire(String key, Long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public Long increment(String key, long delta) {
        return valueOperations.increment(key, delta);
    }

    @Override
    public Long decrement(String key, long delta) {
        return valueOperations.decrement(key, delta);
    }

    @Override
    public void remove(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
    }

    @Override
    public boolean contains(String key) {
        boolean exists = false;
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        Object obj = get(key);
        if (obj != null) {
            exists = true;
        }
        return exists;
    }

    /**
     * 获取过期时间 单位：毫秒
     *
     * @param expireTime 传人的过期时间 单位毫秒 如小于1分钟，默认为10分钟
     **/
    private Long getExpireTime(Long expireTime) {
        Long result = expireTime;
        if (expireTime == null || expireTime < CACHE_MINUTE) {
            result = CACHE_MINUTE;
        }
        return result;
    }


    @Override
    public <T> Boolean setZSet(String key, Long score, T value) {
        return redisTemplate.opsForZSet().add(key, value, Double.longBitsToDouble(score));
    }

    @Override
    public <T> Long get(String key, T value) {
        Double score = redisTemplate.opsForZSet().score(key, value);
        if (ObjectUtils.isNotEmpty(score)) {
            return score.longValue();
        }
        return null;
    }

    @Override
    public <T> T get(String key, Long score) {
        Set<ZSetOperations.TypedTuple<Object>> valueSet = redisTemplate.opsForZSet().rangeByScoreWithScores(key, 0, -1);
        for (ZSetOperations.TypedTuple<Object> objectTypedTuple : valueSet) {
            if (objectTypedTuple.getScore().equals(Double.longBitsToDouble(score))) {
                return (T) objectTypedTuple.getValue();
            }
        }
        return null;
    }

    @Override
    public <K, V> void setHash(String key, K hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public <K> void removeHashEntity(String key, K hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Map getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
}
