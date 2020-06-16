package com.sparksys.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * description: 文件存储启动类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:42:02
 */
@SpringBootApplication(scanBasePackages = {"com.sparksys"})
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients({"com.sparksys"})
public class FileCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCenterApplication.class, args);
    }
}
