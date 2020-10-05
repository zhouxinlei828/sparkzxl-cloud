package com.github.sparkzxl.oauth.interfaces.controller;

import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.oauth.entity.AuthorizationRequest;
import com.github.sparkzxl.oauth.service.OauthService;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;


/**
 * description：授权登录管理
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "授权管理")
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/oauth/token")
    @Trace(operationName = "oauth_get_token_trace")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Basic Auth", paramType = "header", defaultValue = "Basic c3Bhcmt6eGw6MTIzNDU2")
    )
    public OAuth2AccessToken getAccessToken(@RequestHeader(value = "Authorization") String authorization,
                                            Principal principal,
                                            @RequestParam AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
        ActiveSpan.tag("getAccessToken", "get授权登录");
        log.info("Authorization = {}", authorization);
        return oauthService.getAccessToken(principal, authorizationRequest);
    }


    @PostMapping("/oauth/token")
    @Trace(operationName = "oauth_post_token_trace")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Basic Auth", paramType = "header", defaultValue = "Basic c3BhcmtzeXM6MTIzNDU2")
    )
    public OAuth2AccessToken postAccessToken(@RequestHeader(value = "Authorization") String authorization,
                                             @ApiIgnore Principal principal,
                                             @RequestBody AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
        ActiveSpan.tag("postAccessToken", "post授权登录");
        log.info("Authorization = {}", authorization);
        return oauthService.postAccessToken(principal, authorizationRequest);
    }
}
