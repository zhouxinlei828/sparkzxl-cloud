package com.github.sparkzxl.sharding;

import com.github.sparkzxl.boot.SparkBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: ShardingDemo启动类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:21:13
 */
@SpringBootApplication(scanBasePackages = {"com.github.sparkzxl.sharding"})
public class ShardingDemoApplication extends SparkBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingDemoApplication.class, args);
    }
}
