package com.sparksys.file;

import com.sparksys.commons.boot.SparkBootApplication;
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
@EnableDiscoveryClient
@EnableFeignClients({"com.sparksys"})
public class FileCenterApplication extends SparkBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCenterApplication.class, args);
    }
}
