package com.sparksys.oauth.application.service;


import com.sparksys.core.cache.CacheTemplate;
import com.sparksys.oauth.Oauth2Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2Application.class)
@Slf4j
public class CacheProviderServiceTest {


    @Autowired
    private CacheTemplate redisCacheRepository;

    @Autowired
    private CacheTemplate guavaCacheRepository;
    @Test
    public void redisCacheTest() {
        redisCacheRepository.set("test", "datong");
        String data = redisCacheRepository.get("test");
        log.info("data is {}", data);
    }


    @Test
    public void guavaCacheTest() {
        guavaCacheRepository.set("test", "datong");
        String data = guavaCacheRepository.get("test");
        log.info("data is {}", data);
    }

}