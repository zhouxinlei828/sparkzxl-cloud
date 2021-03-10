package com.github.sparkzxl.authorization;

import com.github.sparkzxl.boot.SparkBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
