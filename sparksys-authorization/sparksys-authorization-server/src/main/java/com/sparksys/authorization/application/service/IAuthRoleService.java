package com.sparksys.authorization.application.service;


import com.sparksys.database.base.service.SuperCacheService;
import com.sparksys.authorization.infrastructure.entity.AuthRole;

/**
 * description: 角色 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleService extends SuperCacheService<AuthRole> {

    /**
     * 删除角色信息
     *
     * @param id 角色id
     * @return boolean
     */
    boolean deleteAuthRole(Long id);

    /**
     * 更新角色状态
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @param status 状态
     * @return boolean
     */
    boolean updateAuthRoleStatus(Long userId, Long roleId, Boolean status);
}
