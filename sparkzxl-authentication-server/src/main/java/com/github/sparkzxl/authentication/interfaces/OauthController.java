/*
package com.github.sparkzxl.authentication.interfaces;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.sparkzxl.authentication.domain.AuthUserDetailsService;
import com.github.sparkzxl.core.base.result.ApiResult;
import com.github.sparkzxl.core.entity.CaptchaInfo;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.security.entity.AuthRequest;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;


*/
/**
 * description：授权登录管理
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 *//*

@RestController
@ResponseResult
@WebLog
@Api(tags = "授权管理")
@Slf4j
public class OauthController {

    private final AuthUserDetailsService authUserDetailsService;

    public OauthController(AuthUserDetailsService authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @PostMapping("/oauth/token")
    @Trace(operationName = "oauth_get_token_trace")
    public ApiResult getAccessToken(@RequestBody AuthRequest authRequest) {
        try {
            return ApiResult.apiResult(200, "登录成功", authUserDetailsService.login(authRequest));
        } catch (AccountNotFoundException | PasswordException e) {
            log.error("登陆失败 exception：{}", ExceptionUtil.getMessage(e));
            return ApiResult.apiResult(500, e.getMessage());
        }
    }

    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping(value = "/oauth/captcha")
    public CaptchaInfo captcha(@RequestParam(value = "type") String type) {
        return authUserDetailsService.createCaptcha(type);
    }

    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @GetMapping(value = "/oauth/check")
    public boolean checkCaptcha(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) {
        return authUserDetailsService.checkCaptcha(key, code);
    }

}
*/
