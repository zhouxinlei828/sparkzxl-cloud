package com.github.sparkzxl.authentication.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ResponseResult
@WebLog
@Api(tags = "授权管理")
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping("testKeycloak")
    public AccessToken getAccessTokenInfo(HttpServletRequest request) {
        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        return keycloakSecurityContext.getToken();
    }
}
