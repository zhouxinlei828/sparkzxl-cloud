package com.github.sparkzxl.authorization.application.service;

import com.github.sparkzxl.authorization.infrastructure.entity.RoleOrg;
import com.github.sparkzxl.database.base.service.SuperCacheService;

import java.util.List;

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
     * @return boolean
     */
    boolean deleteRoleOrgByOrgId(Long orgId);

    /**
     * 批量删除角色组织
     *
     * @param orgIds 组织ids
     * @return boolean
     */
    void deleteRoleOrgByOrgIds(List<Long> orgIds);
}
