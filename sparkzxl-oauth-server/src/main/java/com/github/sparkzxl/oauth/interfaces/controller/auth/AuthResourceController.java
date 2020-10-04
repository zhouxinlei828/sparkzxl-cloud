package com.github.sparkzxl.oauth.interfaces.controller.auth;


import com.github.sparkzxl.oauth.application.service.IAuthResourceService;
import com.github.sparkzxl.oauth.infrastructure.entity.AuthResource;
import com.github.sparkzxl.oauth.interfaces.dto.resource.AuthResourcePageDTO;
import com.github.sparkzxl.oauth.interfaces.dto.resource.AuthResourceSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.resource.AuthResourceUpdateDTO;
import com.github.sparkzxl.oauth.interfaces.dto.resource.ResourceQueryDTO;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
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
    public List<AuthResource> visible(@ApiIgnore AuthUserInfo<Long> authUserInfo, ResourceQueryDTO resource) {
        return baseService.findVisibleResource(authUserInfo.getId(), resource);
    }
}
