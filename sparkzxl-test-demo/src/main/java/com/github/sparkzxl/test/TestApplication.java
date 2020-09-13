package com.github.sparkzxl.test;

import com.github.sparkzxl.boot.SparkBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * description: 用户权限系统启动类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:21:13
 */
@SpringBootApplication(scanBasePackages = {"com.github.sparkzxl.test"})
@EnableFeignClients
public class TestApplication extends SparkBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
