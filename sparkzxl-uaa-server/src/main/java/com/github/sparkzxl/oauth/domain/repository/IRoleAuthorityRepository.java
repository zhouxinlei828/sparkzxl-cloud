package com.github.sparkzxl.oauth.domain.repository;

import java.util.Set;

/**
 * description: 角色资源绑定 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:12:43
 */
public interface IRoleAuthorityRepository {

    /**
     * 保存绝色资源
     *
     * @param roleId        角色id
     * @param authorityType 权限类型
     * @param authorityList 资源id
     * @return boolean
     */
    boolean saveRoleAuthorityBatch(Long roleId, String authorityType, Set<Long> authorityList);
}
