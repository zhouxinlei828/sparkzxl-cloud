package com.sparksys.file;

import com.sparksys.boot.SparkBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description: 文件存储启动类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:42:02
 */
@SpringBootApplication(scanBasePackages = {"com.sparksys.file"})
@EnableDiscoveryClient
public class FileCenterApplication extends SparkBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCenterApplication.class, args);
    }
}
