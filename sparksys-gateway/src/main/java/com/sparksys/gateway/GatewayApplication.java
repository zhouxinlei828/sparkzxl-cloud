package com.sparksys.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * description: 网关启动类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:18:18
 */
@SpringBootApplication(scanBasePackages = {"com.sparksys.gateway"})
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients({"com.sparksys.gateway"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
