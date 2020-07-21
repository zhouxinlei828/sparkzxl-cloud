package com.sparksys.oauth.application.service;


import cn.hutool.json.JSONUtil;
import com.sparksys.oauth.interfaces.dto.user.AuthUserPageDTO;
import com.sparksys.web.utils.JacksonUtils;
import com.sparksys.oauth.Oauth2Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2Application.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class AuthUserServiceTest {


    @Autowired
    private IAuthUserService authUserService;

    @Test
    public void authUserPageHelperTest() {
        AuthUserPageDTO authUserPageDTO = new AuthUserPageDTO();
        authUserPageDTO.setAccount("zhouxinlei");
        log.info("data：{}", JacksonUtils.writeJsonAsString(authUserService.listByPage(authUserPageDTO)));
    }

    @Test
    public void getAuthUserDetailTest() {
        log.info("data：{}", JSONUtil.toJsonPrettyStr(authUserService.getAuthUserDetail("zhouxinlei")));
    }

    @Test
    public void getAuthUserTest() {
        log.info("data：{}", JSONUtil.toJsonPrettyStr(authUserService.getCurrentUser("zhouxinlei")));
    }
}
