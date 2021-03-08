package com.github.sparkzxl.authorization.interfaces.controller.oauth;

import com.github.sparkzxl.authorization.application.service.ITenantInfoService;
import com.github.sparkzxl.core.entity.CaptchaInfo;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.open.entity.AuthorizationRequest;
import com.github.sparkzxl.open.service.OauthService;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


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
    private final ITenantInfoService tenantInfoService;

    public OauthController(OauthService oauthService, ITenantInfoService tenantInfoService) {
        this.oauthService = oauthService;
        this.tenantInfoService = tenantInfoService;
    }

    @ApiOperation(value = "GET授权登录端口", notes = "GET授权登录端口")
    @GetMapping("/oauth/token")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Basic Auth", paramType = "header", defaultValue = "Basic c3Bhcmt6eGw6MTIzNDU2")
    )
    public OAuth2AccessToken getAccessToken(@RequestHeader(value = "Authorization") String authorization,
                                            @ApiIgnore Principal principal,
                                            @RequestParam AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
        log.info("Authorization = {}", authorization);
        return oauthService.getAccessToken(principal, authorizationRequest);
    }


    @ApiOperation(value = "POST授权登录端口", notes = "POST授权登录端口")
    @PostMapping("/oauth/token")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Basic Auth", paramType = "header", defaultValue = "Basic c3Bhcmt6eGw6MTIzNDU2")
    )
    public OAuth2AccessToken postAccessToken(@RequestHeader(value = "Authorization") String authorization,
                                             @ApiIgnore Principal principal,
                                             @RequestBody AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
        log.info("Authorization = {}", authorization);
        return oauthService.postAccessToken(principal, authorizationRequest);
    }

    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping(value = "/oauth/captcha")
    public CaptchaInfo captcha(@RequestParam(value = "type") String type) {
        return oauthService.createCaptcha(type);
    }

    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @GetMapping(value = "/oauth/checkCaptcha")
    public boolean checkCaptcha(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) {
        return oauthService.checkCaptcha(key, code);
    }

    @ApiOperation(value = "校验租户信息", notes = "校验租户信息")
    @GetMapping(value = "/oauth/checkTenant")
    public boolean checkTenantCode(@RequestParam(value = "tenantCode") String tenantCode) {
        return tenantInfoService.checkTenantCode(tenantCode);
    }

    @ApiOperation(value = "获取授权登录地址", notes = "获取授权登录地址")
    @GetMapping("/oauth/getAuthorizeUrl")
    public String getAuthorizeUrl(@RequestParam(value = "clientId", required = false) String clientId,
                                  @RequestParam(value = "frontUrl", required = false) String frontUrl) {
        return oauthService.getAuthorizeUrl(clientId, frontUrl);
    }

    @ApiOperation(value = "授权成功回调接口", notes = "授权成功回调接口")
    @GetMapping("/oauth/callBack")
    public OAuth2AccessToken callBack(@RequestParam("code") String code, @RequestParam("state") String state) {
        return oauthService.callBack(code, state);
    }


    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping("customLogout")
    public Integer logout(HttpServletRequest request) {
        return oauthService.logout(request) ? 1 : 0;
    }

}
