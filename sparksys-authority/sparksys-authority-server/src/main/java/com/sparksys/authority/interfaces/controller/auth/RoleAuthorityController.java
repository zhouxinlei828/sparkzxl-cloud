package com.sparksys.authority.interfaces.controller.auth;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sparksys.database.base.controller.SuperCacheController;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.authority.application.service.IRoleAuthorityService;
import com.sparksys.authority.infrastructure.entity.RoleAuthority;
import com.sparksys.authority.interfaces.dto.role.RoleAuthorityPageDTO;
import com.sparksys.authority.interfaces.dto.role.RoleAuthoritySaveDTO;
import com.sparksys.authority.interfaces.dto.role.RoleAuthorityUpdateDTO;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("批量保存角色资源")
    @PostMapping("/batch")
    public boolean saveRoleAuthorityBatch(@Validated @RequestBody RoleAuthoritySaveDTO roleAuthoritySaveDTO) {
        return baseService.saveRoleAuthorityBatch(roleAuthoritySaveDTO);
    }

    @ApiOperation("批量删除角色资源")
    @DeleteMapping("/batch")
    public boolean deleteRoleAuthorityBatch(@RequestParam(value = "roleIds") List<Long> roleIds) {
        return baseService.remove(new LambdaUpdateWrapper<RoleAuthority>().in(RoleAuthority::getRoleId, roleIds));
    }
}
