package com.sparksys.oauth.application.service;


import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.commons.core.utils.common.NumberUtil;
import com.sparksys.commons.database.utils.PageInfoUtils;
import com.sparksys.commons.web.utils.JacksonUtils;
import com.sparksys.oauth.Oauth2Application;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.mapper.AuthUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2Application.class)
@Slf4j
public class AuthUserServiceTest {


    @Autowired
    private IAuthUserService authUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void authUserPageHelperTest() {
        log.info("data：{}", JacksonUtils.writeJsonAsString(authUserService.listByPage(10,10,null)));
    }


    @Test
    public void authUserInsertTest() {
        List<AuthUser> authUsers = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            AuthUser authUser = new AuthUser();
            authUser.setAccount(RandomStringUtils.randomAlphabetic(8));
            authUser.setName(RandomStringUtils.randomAlphabetic(4));
            authUser.setOrgId(643776594376135105L);
            authUser.setSex(1);
            authUser.setStationId(645200151886964289L);
            authUser.setEmail(RandomUtil.randomNumbers(9).concat("@qq.com"));
            authUser.setMobile(NumberUtil.getRandom(11));
            authUser.setStatus(Boolean.TRUE);
            authUser.setPassword(passwordEncoder.encode("123456"));
            authUser.setCreateUser(1248084109452902400L);
            authUser.setUpdateUser(1248084109452902400L);
            authUsers.add(authUser);
        }
        authUserService.saveBatchSomeColumn(authUsers);
    }

}