package com.sparksys.oauth.application.service;


import cn.hutool.json.JSONUtil;
import com.sparksys.core.cache.CacheTemplate;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.database.entity.RemoteData;
import com.sparksys.oauth.Oauth2Application;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.entity.CoreOrg;
import com.sparksys.oauth.infrastructure.enums.SexEnum;
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
    private CacheTemplate redisCacheTemplate;

    @Autowired
    private CacheTemplate guavaCacheRepository;
    @Test
    public void redisCacheTest() {
        AuthUser authUser = new AuthUser();
        authUser.setAccount("zhouxinlei");
        authUser.setId(21424234234L);
        authUser.setName("周鑫磊");
        authUser.setStatus(true);
        authUser.setSex(SexEnum.MAN);
        RemoteData<Long, CoreOrg> remoteData = new RemoteData<>();
        remoteData.setKey(213423423L);
        CoreOrg coreOrg = new CoreOrg();
        coreOrg.setId(46438968034L);
        coreOrg.setDescribe("345345345");
        remoteData.setData(coreOrg);
        authUser.setOrg(remoteData);
        redisCacheTemplate.set("test-zhouxinlei", authUser);
        AuthUser data = redisCacheTemplate.get("test-zhouxinlei");
        log.info("data is {}", JSONUtil.toJsonPrettyStr(data));
    }


    @Test
    public void guavaCacheTest() {
        guavaCacheRepository.set("test", "datong");
        String data = guavaCacheRepository.get("test");
        log.info("data is {}", data);
    }

}