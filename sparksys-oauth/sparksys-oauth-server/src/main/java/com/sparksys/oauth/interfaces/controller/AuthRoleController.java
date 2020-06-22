package com.sparksys.oauth.interfaces.controller;

import com.sparksys.oauth.application.service.IAuthRoleService;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleUpdateDTO;
import com.sparksys.commons.core.base.api.result.ApiPageResult;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * description: 角色 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:40:03
 */
@RestController
@RequestMapping("/role")
@ResponseResult
@Api(tags = "角色管理")
public class AuthRoleController {

    private final IAuthRoleService authRoleService;

    public AuthRoleController(IAuthRoleService authRoleService) {
        this.authRoleService = authRoleService;
    }


    @ApiOperation("查询角色列表")
    @GetMapping("/role/page")
    public ApiPageResult listByPage(Integer pageNum, Integer pageSize, @RequestParam(value = "name", required = false) String name) {
        return authRoleService.listByPage(pageNum, pageSize, name);
    }

    @ApiOperation("获取角色信息")
    @GetMapping("/role/{id}")
    public AuthRoleDTO getAuthRole(@PathVariable("id") Long id) {
        return authRoleService.getAuthRole(id);
    }


    @ApiOperation("保存角色信息")
    @PostMapping("/role")
    public boolean saveAuthRole(AuthUser authUser, @Validated @RequestBody AuthRoleSaveDTO authRoleSaveDTO) {
        return authRoleService.saveAuthRole(authUser, authRoleSaveDTO);
    }

    @ApiOperation("更新角色信息")
    @PutMapping("/role")
    public boolean updateAuthRole(AuthUser authUser, @Validated @RequestBody AuthRoleUpdateDTO authRoleUpdateDTO) {
        return authRoleService.updateAuthRole(authUser, authRoleUpdateDTO);
    }

    @ApiOperation("删除角色信息")
    @DeleteMapping("/role/{id}")
    public boolean deleteAuthRole(@PathVariable("id") Long id) {
        return authRoleService.deleteAuthRole(id);
    }

    @ApiOperation("更新角色状态")
    @PatchMapping("/role/{id}")
    public boolean updateAuthRoleStatus(AuthUser authUser, @PathVariable("id") Long id, @RequestParam(value = "status") Boolean status) {
        return authRoleService.updateAuthRoleStatus(authUser.getId(), id, status);
    }
}
