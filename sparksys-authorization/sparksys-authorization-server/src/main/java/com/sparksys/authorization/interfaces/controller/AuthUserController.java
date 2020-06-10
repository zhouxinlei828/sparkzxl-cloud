package com.sparksys.authorization.interfaces.controller;


import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.interfaces.dto.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserUpdateDTO;
import com.sparksys.commons.core.api.result.ApiPageResult;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.mybatis.dto.PageDTO;
import com.sparksys.commons.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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


    @ApiOperation("查询用户列表")
    @GetMapping("/authUser/page")
    public ApiPageResult listByPage(Integer pageNum, Integer pageSize, @RequestParam(value = "name",required = false) String name) {
        return authUserService.listByPage(pageNum, pageSize, name);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/authUser/{id}")
    public AuthUserDTO updateAuthUser(@PathVariable("id") Long id) {
        return authUserService.getAuthUser(id);
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/authUser")
    public boolean saveAuthUser(AuthUser authUser, @Validated @RequestBody AuthUserSaveDTO authUserSaveDTO) {
        return authUserService.saveAuthUser(authUser, authUserSaveDTO);
    }


    @ApiOperation("修改用户信息")
    @PutMapping("/authUser")
    public boolean updateAuthUser(AuthUser authUser, @Validated @RequestBody AuthUserUpdateDTO authUserUpdateDTO) {
        return authUserService.updateAuthUser(authUser, authUserUpdateDTO);
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/authUser")
    public boolean deleteAuthUser(@RequestParam(value = "id") Long id) {
        return authUserService.deleteAuthUser(id);
    }

    @ApiOperation("修改用户状态信息")
    @PatchMapping("/authUser")
    public boolean updateAuthUserStatus(AuthUser authUser, @Validated @RequestBody AuthUserStatusDTO authUserStatusDTO) {
        return authUserService.updateAuthUserStatus(authUser, authUserStatusDTO);
    }


}
