package com.sparksys.oauth.interfaces.controller.auth;


import com.sparksys.database.base.controller.SuperCacheController;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.IRoleAuthorityService;
import com.sparksys.oauth.infrastructure.entity.RoleAuthority;
import com.sparksys.oauth.interfaces.dto.role.RoleAuthorityPageDTO;
import com.sparksys.oauth.interfaces.dto.role.RoleAuthoritySaveDTO;
import com.sparksys.oauth.interfaces.dto.role.RoleAuthorityUpdateDTO;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * description: 角色资源管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:05:10
 */
@RestController
@RequestMapping("/role/authority")
@ResponseResult
@WebLog
@Api(tags = "角色资源管理")
public class RoleAuthorityController extends SuperCacheController<IRoleAuthorityService, Long,
        RoleAuthority, RoleAuthorityPageDTO, RoleAuthoritySaveDTO, RoleAuthorityUpdateDTO> {

}
