package com.sparksys.authorization.interfaces.controller;

import com.sparksys.authorization.domain.service.AuthUserDetailsService;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.utils.ResponseResultUtils;
import com.sparksys.commons.log.annotation.WebLog;
import com.sparksys.commons.security.dto.LoginDTO;
import com.sparksys.commons.security.entity.AuthToken;
import com.sparksys.commons.security.properties.SecurityProperties;
import com.sparksys.commons.user.service.IGlobalUserService;
import com.sparksys.commons.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * description：登录Controller
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 */
@RestController
@RequestMapping("/oauth")
@ResponseResult
@WebLog
@Api(tags = "登录管理")
public class OauthController {

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private IGlobalUserService globalUserService;

    @ApiOperation("系统登录")
    @PostMapping("/login")
    public AuthToken login(@Validated @RequestBody LoginDTO loginDto) {
        return authUserDetailsService.login(loginDto);
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/getAuthUserInfo")
    public GlobalAuthUser getUserInfo(HttpServletRequest httpServletRequest) {
        String accessToken = ResponseResultUtils.getAuthHeader(httpServletRequest);
        return globalUserService.getUserInfo(accessToken);
    }

    @ApiOperation("获取配置信息")
    @GetMapping("/getSecurityProperties")
    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }
}
