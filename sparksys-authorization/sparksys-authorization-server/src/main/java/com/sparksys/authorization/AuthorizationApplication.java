package com.sparksys.authorization;

import com.sparksys.boot.SparkBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * description: 用户权限系统启动类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:21:13
 */
@SpringBootApplication(scanBasePackages = {"com.sparksys.authorization"})
@EnableFeignClients
public class AuthorizationApplication extends SparkBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}
