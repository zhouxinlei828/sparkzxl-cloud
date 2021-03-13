package com.github.sparkzxl.authorization.interfaces.controller.oauth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description: 登录管理
 *
 * @author: zhouxinlei
 * @date: 2021-03-13 18:58:25
 */
@Controller
@Api(tags = "登录管理")
public class LoginController {

    @ApiOperation(value = "登录页面", notes = "登录页面")
    @GetMapping(value = "/authentication/require", produces = "text/html;charset=UTF-8")
    public String require() {
        return "login";
    }
}
