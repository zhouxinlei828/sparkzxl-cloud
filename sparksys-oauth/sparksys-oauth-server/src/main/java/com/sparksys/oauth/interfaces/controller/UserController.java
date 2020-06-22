package com.sparksys.oauth.interfaces.controller;

import com.sparksys.commons.web.annotation.ResponseResult;
import com.sparksys.commons.web.utils.HttpResponseUtils;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * description：授权登录管理
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 */
@RestController
@RequestMapping("/user")
@ResponseResult
@Api(tags = "授权登录管理")
public class UserController {

    @GetMapping("/getCurrentUser")
    @ApiOperation("获取当前用户")
    public Object getCurrentUser(HttpServletRequest httpRequest) {
        String token = HttpResponseUtils.getAuthHeader(httpRequest);
        return Jwts.parser()
                .setSigningKey("sparksys".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
