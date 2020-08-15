package com.sparksys.authority.interfaces.controller.auth;

import com.sparksys.authority.interfaces.dto.user.AuthUserDTO;
import com.sparksys.authority.interfaces.dto.user.AuthUserPageDTO;
import com.sparksys.authority.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.authority.interfaces.dto.user.AuthUserUpdateDTO;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.database.base.controller.SuperCacheController;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.authority.application.service.IAuthUserService;
import com.sparksys.authority.infrastructure.entity.AuthUser;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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

    private final PasswordEncoder passwordEncoder;

    public AuthUserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

    @Override
    public boolean handlerDelete(List<Long> ids) {
        baseService.deleteUserRelation(ids);
        return true;
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/currentUser")
    public AuthUserInfo getCurrentUser(@ApiIgnore AuthUserInfo authUserInfo) {
        return authUserInfo;
    }
}
