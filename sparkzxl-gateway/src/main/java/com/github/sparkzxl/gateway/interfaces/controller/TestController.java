package com.github.sparkzxl.gateway.interfaces.controller;

import com.github.sparkzxl.core.jackson.JsonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "授权管理")
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping("testKeycloak")
    public OAuth2User getOAuth2AuthenticationToken(@AuthenticationPrincipal OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        log.info("获取当前登录人token，{}", JsonUtil.toJson(oAuth2AuthenticationToken.getPrincipal()));
        return oAuth2AuthenticationToken.getPrincipal();
    }
}
