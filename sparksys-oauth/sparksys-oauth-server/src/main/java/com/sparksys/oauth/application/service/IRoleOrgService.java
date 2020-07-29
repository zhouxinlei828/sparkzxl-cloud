package com.sparksys.oauth.application.service;

import com.sparksys.database.base.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.RoleOrg;

/**
 * <p>
 * 角色组织关系 服务类
 * </p>
 *
 * @author zhouxinlei
 * @since 2020-07-19
 */
public interface IRoleOrgService extends SuperCacheService<RoleOrg> {

    /**
     * 删除角色组织
     *
     * @param orgId 组织id
     * @return
     */
    boolean deleteRoleOrgByOrgId(Long orgId);
}
