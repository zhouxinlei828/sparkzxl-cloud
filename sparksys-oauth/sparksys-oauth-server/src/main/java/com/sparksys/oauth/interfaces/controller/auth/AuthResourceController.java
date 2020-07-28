package com.sparksys.oauth.interfaces.controller.auth;


import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.IAuthResourceService;
import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.oauth.interfaces.dto.resource.AuthResourceDTO;
import com.sparksys.oauth.interfaces.dto.resource.ResourceQueryDTO;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * description: 资源 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:39:30
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "资源管理")
public class AuthResourceController {

    private final IAuthResourceService authResourceService;

    public AuthResourceController(IAuthResourceService authResourceService) {
        this.authResourceService = authResourceService;
    }

    @ApiOperation("查询菜单列表")
    @GetMapping("/resources")
    public List<AuthResourceDTO> resourceList() {
        return authResourceService.resourceList();
    }

    @ApiOperation("查询用户可用的所有资源")
    @GetMapping("/visible")
    public List<AuthResource> visible(@ApiIgnore AuthUserInfo authUserInfo, ResourceQueryDTO resource) {
        return authResourceService.findVisibleResource(authUserInfo.getId(), resource);
    }

}
