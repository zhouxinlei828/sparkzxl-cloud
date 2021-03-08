package com.github.sparkzxl.authorization;

import com.github.sparkzxl.authorization.infrastructure.netty.NettyServer;
import com.github.sparkzxl.boot.SparkBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * description: 认证授权启动类
 *
 * @author: zhouxinlei
 * @date: 2021-02-01 11:18:40
 */
@SpringBootApplication(scanBasePackages = {"com.github.sparkzxl.authorization"})
@Slf4j
public class AuthorizationServerApplication extends SparkBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AuthorizationServerApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        try {
            new NettyServer(12345, "/ws").start();
            log.info("NettyServer 启动成功:{}:{}/{}",
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"), "/ws");
        } catch (Exception e) {
            log.error("NettyServerError:{}", e.getMessage());
        }
    }
}
