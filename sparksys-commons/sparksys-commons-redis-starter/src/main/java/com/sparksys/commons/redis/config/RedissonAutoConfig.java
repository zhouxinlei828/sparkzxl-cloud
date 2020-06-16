package com.sparksys.commons.redis.config;

import com.sparksys.commons.redis.props.RedissonProperties;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * description：Redisson配置
 *
 * @author zhouxinlei
 * @date  2020/6/10 0010
 */
@Configuration
@ConditionalOnClass(Config.class)
public class RedissonAutoConfig {


    @Autowired
    private RedissonProperties redssionProperties;


    @Bean
    @ConditionalOnProperty(name = "redisson.address")
    public RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redssionProperties.getAddress())
                .setTimeout(redssionProperties.getTimeout())
                .setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());
        if (StringUtils.isNotBlank(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }
        return Redisson.create(config);
    }

    @Bean
    RedissLockUtil redissLockUtil(@Qualifier("redissonSingle") RedissonClient redissonClient) {
        RedissLockUtil redissLockUtil = new RedissLockUtil();
        redissLockUtil.setRedissonClient(redissonClient);
        return redissLockUtil;
    }

}
