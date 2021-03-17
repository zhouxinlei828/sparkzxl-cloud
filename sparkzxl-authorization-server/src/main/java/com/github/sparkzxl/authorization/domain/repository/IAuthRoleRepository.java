package com.github.sparkzxl.authorization.domain.repository;


import com.github.sparkzxl.authorization.infrastructure.entity.AuthRole;

import java.util.List;

/**
 * description: 角色 仓储类
 *
 * @author charles.zhou
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleRepository {

    /**
     * 删除角色以及关联信息
     *
     * @param ids ids
     */
    void deleteAuthRoleRelation(List<Long> ids);

    /**
     * 保存角色
     *
     * @param authRole 角色信息
     */
    void saveRole(AuthRole authRole);

    /**
     * 根据租户code删除角色信息
     * @param tenantCode 租户code
     */
    void deleteAuthRole(String tenantCode);
}
