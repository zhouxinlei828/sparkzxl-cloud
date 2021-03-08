package com.github.sparkzxl.authorization.interfaces.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "登录页管理")
public class LoginController {

    @ApiOperation(value = "登录页面", notes = "登录页面")
    @GetMapping(value = "/authentication/require",produces = "text/html;charset=UTF-8")
    public String require() {
        return "login";
    }
}
