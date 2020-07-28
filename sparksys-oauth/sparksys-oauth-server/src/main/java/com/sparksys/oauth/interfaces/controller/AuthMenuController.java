package com.sparksys.oauth.interfaces.controller;


import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.IAuthMenuService;
import com.sparksys.oauth.infrastructure.entity.AuthMenu;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 菜单 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:39:14
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "菜单管理")
public class AuthMenuController {

    private final IAuthMenuService authMenuService;


    public AuthMenuController(IAuthMenuService authMenuService) {
        this.authMenuService = authMenuService;
    }

    @ApiOperation("查询菜单列表")
    @GetMapping("/menu/tree")
    public List<AuthMenu> findMenuTree() {
        return authMenuService.findMenuTree();
    }
}
