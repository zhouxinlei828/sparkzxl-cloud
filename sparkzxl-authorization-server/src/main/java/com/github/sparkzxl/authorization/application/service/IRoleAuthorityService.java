package com.github.sparkzxl.authorization.application.service;

import com.github.sparkzxl.authorization.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.authorization.interfaces.dto.role.RoleAuthoritySaveDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;

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
