package com.github.sparkzxl.gateway.infrastructure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:  oauth resource属性
 *
 * @author: zhouxinlei
 * @date: 2020-08-01 13:24:15
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.security.oauth2.resource")
public class ResourceProperties {

    /**
     * 需要放行的资源路径
     */
    private String[] ignorePatterns;

}
