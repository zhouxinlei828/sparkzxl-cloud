package com.sparksys.authorization.interfaces.facade;


import cn.hutool.core.util.StrUtil;
import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.infrastructure.po.AuthUserDetail;
import com.sparksys.authorization.interfaces.dto.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserLoginDTO;
import com.sparksys.commons.core.constant.CoreConstant;
import com.sparksys.commons.web.annotation.ResponseResult;
import com.sparksys.commons.web.utils.HttpServletUtils;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description: 用户 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:25:32
 */

@RestController
@ResponseResult
@RequestMapping("/user")
@Api(tags = "用户管理")
public class AuthUserController {

    @Autowired
    private IAuthUserService authUserService;

    @GetMapping("/getCurrentUser")
    @ApiOperation("获取当前用户")
    public Object getCurrentUser() {
        String header = HttpServletUtils.getRequest().getHeader("Authorization");
        String token = StrUtil.subAfter(header, CoreConstant.JwtTokenConstant.JWT_TOKEN_HEAD, false);
        return Jwts.parser()
                .setSigningKey("sparksys".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    @PostMapping("getAuthUserDetail")
    @ApiOperation("获取当前用户")
    public AuthUserDTO getAuthUserDetail(@RequestBody AuthUserLoginDTO authUserLoginDTO) {
        return authUserService.getAuthUserDetail(authUserLoginDTO);
    }

}
