package com.github.sparkzxl.activiti;

import com.github.sparkzxl.boot.SparkBootApplication;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: activiti工作流引擎启动类
 *
 * @author: zhouxinlei
 * @date: 2020-07-16 20:01:10
 */
@SpringBootApplication(scanBasePackages = {"com.github.sparkzxl.activiti"}, exclude = SecurityAutoConfiguration.class)
public class ActivitiApplication extends SparkBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }

}
