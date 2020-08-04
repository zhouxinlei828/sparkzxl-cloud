package com.sparksys.oauth.infrastructure.constant;

/**
 * description: 缓存常量
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 11:01:07
 */
public class CacheConstant {

    /**
     * 用户 前缀
     * 完整key: user:classTypeName:{USER_ID} -> [ROLE_ID, ...]
     */
    public static String USER = "user";

    /**
     * 登录总次数
     * login_log_total:{TENANT} -> Long
     */
    public static String LOGIN_LOG_TOTAL = "login_log_total";
    /**
     * 今日登录总次数
     * login_log_today:{TENANT}:{today} -> Long
     */
    public static String LOGIN_LOG_TODAY = "login_log_today";
    /**
     * 今日登录总ip
     * login_log_todayip:{TENANT}:{today} -> Map
     */
    public static String LOGIN_LOG_TODAY_IP = "login_log_todayip";
    /**
     * 最近10访问记录
     * login_log_tenday:{TENANT}:{today}:{account} -> Map
     */
    public static String LOGIN_LOG_TEN_DAY = "login_log_tenday";
    /**
     * 登录总次数
     * login_log_browser:{TENANT} -> Map
     */
    public static String LOGIN_LOG_BROWSER = "login_log_browser";
    /**
     * 登录总次数
     * login_log_system{TENANT} -> Map
     */
    public static String LOGIN_LOG_SYSTEM = "login_log_system";

}
