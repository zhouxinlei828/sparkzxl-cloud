package com.sparksys.commons.mybatis.context;

import cn.hutool.core.convert.Convert;

import java.util.HashMap;
import java.util.Map;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020-06-19 10:32:21
*/
public class BaseContextHandler {

    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal();

    public BaseContextHandler() {
    }

    public static void set(String key, Object value) {
        Map<String, String> map = getLocalMap();
        map.put(key, value == null ? "" : value.toString());
    }

    public static <T> T get(String key, Class<T> type) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.get(key));
    }

    public static <T> T get(String key, Class<T> type, Object def) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.getOrDefault(key, String.valueOf(def == null ? "" : def)));
    }

    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return (String)map.getOrDefault(key, "");
    }

    public static Map<String, String> getLocalMap() {
        Map<String, String> map = (Map)THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap(10);
            THREAD_LOCAL.set(map);
        }

        return (Map)map;
    }

    public static void setLocalMap(Map<String, String> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static Long getUserId() {
        return (Long)get("userid", Long.class, 0L);
    }

    public static String getUserIdStr() {
        return String.valueOf(getUserId());
    }

    public static void setUserId(Long userId) {
        set("userid", userId);
    }

    public static void setUserId(String userId) {
        set("userid", userId);
    }

    public static String getAccount() {
        return (String)get("account", String.class);
    }

    public static void setAccount(String name) {
        set("account", name);
    }

    public static String getName() {
        return (String)get("name", String.class);
    }

    public static void setName(String account) {
        set("name", account);
    }

    public static String getToken() {
        return (String)get("token", String.class);
    }

    public static void setToken(String token) {
        set("token", token);
    }

    public static void remove() {
        if (THREAD_LOCAL != null) {
            THREAD_LOCAL.remove();
        }
    }

}
