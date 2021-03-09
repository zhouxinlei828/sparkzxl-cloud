package com.github.sparkzxl.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description: 文件存储启动类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:42:02
 */
@SpringBootApplication(scanBasePackages = {"com.github.sparkzxl.job"})
@EnableDiscoveryClient
public class WorkJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkJobApplication.class, args);
    }
}
