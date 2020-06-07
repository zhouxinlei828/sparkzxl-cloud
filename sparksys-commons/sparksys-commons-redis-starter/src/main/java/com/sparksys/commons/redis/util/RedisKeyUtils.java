package com.sparksys.commons.redis.util;

import cn.hutool.core.util.StrUtil;

/**
 * description：redis key处理
 *
 * @Author zhouxinlei
 * @Date 2020/6/7 11:45 上午
 */
public class RedisKeyUtils {

    public static <T> String getRedisKey(String template, T t) {
        return StrUtil.format(template, t);
    }
}
