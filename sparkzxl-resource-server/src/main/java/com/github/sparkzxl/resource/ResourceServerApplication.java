package com.github.sparkzxl.resource;

import com.github.sparkzxl.boot.SparkBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * description:
 *
 * @author zhouxinlei
 * @date 2021-02-23 09:44:22
*/
@SpringBootApplication
@EnableOAuth2Sso
public class ResourceServerApplication extends SparkBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}
