package com.sparksys.gateway.infrastructure.resource;

import java.util.Arrays;
import java.util.List;

/**
 * description: 静态资源放行配置
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:17:37
 */
public class StaticResource {

    public static final List<String> EXCLUDE_PATTERNS = Arrays.asList(
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/swagger/**",
            "/**/v2/api-docs",
            "/**/*.js",
            "/**/*.css",
            "/**/*.png",
            "/**/*.ico",
            "/webjars/springfox-swagger-ui/**",
            "/doc.html",
            "/actuator/**",
            "/druid/**"
    );

}
