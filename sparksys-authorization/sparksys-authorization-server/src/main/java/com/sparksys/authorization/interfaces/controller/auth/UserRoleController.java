package com.sparksys.authorization.interfaces.controller.auth;


import com.sparksys.log.annotation.WebLog;
import com.sparksys.authorization.application.service.IUserRoleService;
import com.sparksys.authorization.interfaces.dto.role.RoleUserDTO;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 账号角色绑定 前端控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:07:12
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "角色管理")
public class UserRoleController {

    private final IUserRoleService userRoleService;

    public UserRoleController(IUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @ApiOperation("保存角色用户")
    @PostMapping("/role/user")
    public boolean saveAuthRoleUser(@Validated @RequestBody RoleUserDTO roleUserDTO) {
        return userRoleService.saveAuthRoleUser(roleUserDTO);
    }


    @ApiOperation("删除角色用户")
    @DeleteMapping("/role/user")
    public boolean deleteAuthRoleUser(@Validated @RequestBody RoleUserDTO roleUserDTO) {
        return userRoleService.deleteAuthRoleUser(roleUserDTO);
    }

}
