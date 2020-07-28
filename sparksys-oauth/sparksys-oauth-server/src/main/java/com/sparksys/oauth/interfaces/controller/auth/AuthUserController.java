package com.sparksys.oauth.interfaces.controller.auth;


import com.github.pagehelper.PageInfo;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.IAuthUserService;
import com.sparksys.oauth.interfaces.dto.user.*;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * description: 用户 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:25:32
 */

@RestController
@ResponseResult
@WebLog
@Api(tags = "用户管理")
public class AuthUserController {

    private final IAuthUserService authUserService;

    public AuthUserController(IAuthUserService authUserService) {
        this.authUserService = authUserService;
    }


    @ApiOperation("查询用户列表")
    @GetMapping("/authUser/page")
    public PageInfo<AuthUserDTO> listByPage(AuthUserPageDTO authUserPageDTO) {
        return authUserService.listByPage(authUserPageDTO);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/authUser/{id}")
    public AuthUserDTO updateAuthUser(@PathVariable("id") Long id) {
        return authUserService.getAuthUser(id);
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/authUser")
    public boolean saveAuthUser(@ApiIgnore AuthUserInfo authUser, @Validated @RequestBody AuthUserSaveDTO authUserSaveDTO) {
        return authUserService.saveAuthUser(authUser.getId(), authUserSaveDTO);
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/authUser")
    public boolean updateAuthUser(@ApiIgnore AuthUserInfo authUser, @Validated @RequestBody AuthUserUpdateDTO authUserUpdateDTO) {
        return authUserService.updateAuthUser(authUser.getId(), authUserUpdateDTO);
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/authUser/{id}")
    public boolean deleteAuthUser(@PathVariable("id") Long id) {
        return authUserService.deleteAuthUser(id);
    }

    @ApiOperation("修改用户状态信息")
    @PatchMapping("/authUser")
    public boolean updateAuthUserStatus(@ApiIgnore AuthUserInfo authUser, @Validated @RequestBody AuthUserStatusDTO authUserStatusDTO) {
        return authUserService.updateAuthUserStatus(authUser.getId(), authUserStatusDTO);
    }

}
