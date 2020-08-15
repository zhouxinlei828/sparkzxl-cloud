package com.sparksys.authorization.application.service;

import com.sparksys.authorization.interfaces.dto.role.RoleAuthoritySaveDTO;
import com.sparksys.database.base.service.SuperCacheService;
import com.sparksys.authorization.infrastructure.entity.RoleAuthority;

/**
 * <p>
 * 角色的资源 服务类
 * </p>
 *
 * @author zhouxinlei
 * @since 2020-07-19
 */
public interface IRoleAuthorityService extends SuperCacheService<RoleAuthority> {

    /**
     * 保存角色资源
     *
     * @param roleAuthoritySaveDTO 角色资源保存对象
     * @return boolean
     */
    boolean saveRoleAuthorityBatch(RoleAuthoritySaveDTO roleAuthoritySaveDTO);
}
