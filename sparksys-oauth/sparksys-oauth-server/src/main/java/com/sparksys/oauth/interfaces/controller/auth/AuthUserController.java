package com.sparksys.oauth.interfaces.controller.auth;

import com.sparksys.database.base.controller.SuperCacheController;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.IAuthUserService;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.interfaces.dto.user.*;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * description: 用户管理
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:25:32
 */

@RestController
@RequestMapping("/user")
@ResponseResult
@WebLog
@Api(tags = "用户管理")
public class AuthUserController extends SuperCacheController<IAuthUserService, Long,
        AuthUser, AuthUserPageDTO, AuthUserSaveDTO, AuthUserUpdateDTO> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("获取用户信息")
    @GetMapping("/authUser/{id}")
    public AuthUserDTO updateAuthUser(@PathVariable("id") Long id) {
        return baseService.getAuthUser(id);
    }

    @Override
    public boolean handlerSave(AuthUserSaveDTO model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        return true;
    }
}
