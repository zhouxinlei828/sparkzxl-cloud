package com.sparksys.oauth.interfaces.controller;

import com.sparksys.commons.oauth.service.OauthService;
import com.sparksys.commons.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


/**
 * description：授权登录管理
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 */
@RestController
@RequestMapping("/oauth")
@ResponseResult
@Api(tags = "授权登录管理")
public class OauthController {

    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/token")
    public OAuth2AccessToken getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return oauthService.getAccessToken(principal, parameters);
    }

    @PostMapping("/token")
    public OAuth2AccessToken postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return oauthService.postAccessToken(principal, parameters);
    }
}
