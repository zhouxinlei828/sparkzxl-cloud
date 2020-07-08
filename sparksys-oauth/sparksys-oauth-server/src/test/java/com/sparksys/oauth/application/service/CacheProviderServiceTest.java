package com.sparksys.oauth.application.service;

import com.sparksys.commons.core.cache.CacheProviderService;
import com.sparksys.oauth.Oauth2Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2Application.class)
@Slf4j
public class CacheProviderServiceTest {


    @Autowired
    @Qualifier("redisCache")
    private CacheProviderService redisCache;

    @Autowired
    @Qualifier("guavaCache")
    private CacheProviderService guavaCache;
    @Test
    public void redisCacheTest() {
        redisCache.set("test", "datong");
        String data = redisCache.get("test");
        log.info("data is {}", data);
    }


    @Test
    public void guavaCacheTest() {
        guavaCache.set("test", "datong");
        String data = guavaCache.get("test");
        log.info("data is {}", data);
    }

}