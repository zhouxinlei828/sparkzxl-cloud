package com.sparksys.commons.redis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/10 0010
 */

@ConfigurationProperties(prefix = "redisson")
@Component
@Data
public class RedissonProperties {

    private int timeout;

    private String address;

    private String password;

    private int connectionPoolSize;

    private int connectionMinimumIdleSize;

    private int slaveConnectionPoolSize;

    private int masterConnectionPoolSize;

    private String[] sentinelAddresses;

    private String masterName;

}
