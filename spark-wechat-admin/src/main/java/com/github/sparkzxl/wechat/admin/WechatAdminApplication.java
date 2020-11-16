package com.github.sparkzxl.wechat.admin;

import com.github.sparkzxl.boot.SparkBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: 微信应用启动类
 *
 * @author: zhouxinlei
 * @date: 2020-07-16 20:01:10
 */
@SpringBootApplication(scanBasePackages = {"com.github.sparkzxl.wechat.admin"})
public class WechatAdminApplication extends SparkBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatAdminApplication.class, args);
    }

}
