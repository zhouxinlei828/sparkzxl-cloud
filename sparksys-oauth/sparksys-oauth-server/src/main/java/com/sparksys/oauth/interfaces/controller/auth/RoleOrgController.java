package com.sparksys.oauth.interfaces.controller.auth;


import com.sparksys.database.base.controller.SuperCacheController;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.IRoleOrgService;
import com.sparksys.oauth.infrastructure.entity.RoleOrg;
import com.sparksys.oauth.interfaces.dto.role.RoleOrgPageDTO;
import com.sparksys.oauth.interfaces.dto.role.RoleOrgSaveDTO;
import com.sparksys.oauth.interfaces.dto.role.RoleOrgUpdateDTO;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * description: 角色组织关系管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:07:35
 */
@RestController
@RequestMapping("/role/org")
@ResponseResult
@WebLog
@Api(tags = "角色组织关系管理")
public class RoleOrgController extends SuperCacheController<IRoleOrgService, Long,
        RoleOrg, RoleOrgPageDTO, RoleOrgSaveDTO, RoleOrgUpdateDTO> {

}
