package com.sparksys.oauth.interfaces.controller.auth;


import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.database.base.controller.SuperCacheController;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.IAuthResourceService;
import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.oauth.interfaces.dto.resource.AuthResourcePageDTO;
import com.sparksys.oauth.interfaces.dto.resource.AuthResourceSaveDTO;
import com.sparksys.oauth.interfaces.dto.resource.AuthResourceUpdateDTO;
import com.sparksys.oauth.interfaces.dto.resource.ResourceQueryDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRolePageDTO;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * description: 资源管理
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:39:30
 */
@RestController
@RequestMapping("/resource")
@ResponseResult
@WebLog
@Api(tags = "资源管理")
public class AuthResourceController extends SuperCacheController<IAuthResourceService, Long,
        AuthResource, AuthResourcePageDTO, AuthResourceSaveDTO, AuthResourceUpdateDTO> {


    @ApiOperation("查询用户可用的所有资源")
    @GetMapping("/visible")
    public List<AuthResource> visible(@ApiIgnore AuthUserInfo authUserInfo, ResourceQueryDTO resource) {
        return baseService.findVisibleResource(authUserInfo.getId(), resource);
    }

}
