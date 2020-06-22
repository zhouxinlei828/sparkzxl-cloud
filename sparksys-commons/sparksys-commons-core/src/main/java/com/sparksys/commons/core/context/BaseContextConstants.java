package com.sparksys.commons.core.context;

/**
 * description：应用上下文常量池
 *
 * @author zhouxinlei
 * @date 2020-06-19 10:32:21
 */
public class BaseContextConstants {

    public static final String AUTH_USER = "authuser:";

    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";
    public static final String JWT_KEY_TOKEN_TYPE = "token_type";
    public static final String JWT_KEY_ACCOUNT = "account";
    public static final String JWT_KEY_CLIENT_ID = "client_id";
    public static final String JWT_SIGN_KEY = "sparksys";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";
    public static final String BEARER_HEADER_KEY = "token";
    public static final String BEARER_HEADER_PREFIX = "Bearer ";
    public static final String BEARER_HEADER_PREFIX_EXT = "Bearer%20";
    public static final String BASIC_HEADER_KEY = "Authorization";
    public static final String BASIC_HEADER_PREFIX = "Basic ";
    public static final String BASIC_HEADER_PREFIX_EXT = "Basic%20";
    public static final String IS_BOOT = "boot";
    public static final String TRACE_ID_HEADER = "x-trace-header";
    public static final String LOG_TRACE_ID = "trace";
    public static final String GRAY_VERSION = "grayversion";

}
