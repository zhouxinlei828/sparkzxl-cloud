package com.github.sparkzxl.authorization.interfaces.controller.auth;


import com.github.sparkzxl.authorization.application.service.IRoleOrgService;
import com.github.sparkzxl.authorization.infrastructure.entity.RoleOrg;
import com.github.sparkzxl.authorization.interfaces.dto.role.RoleOrgPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.role.RoleOrgSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.role.RoleOrgUpdateDTO;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.core.annotation.ResponseResult;
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
