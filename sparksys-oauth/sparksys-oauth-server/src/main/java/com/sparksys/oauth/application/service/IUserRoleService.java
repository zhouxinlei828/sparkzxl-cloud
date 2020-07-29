package com.sparksys.oauth.application.service;


import com.sparksys.database.base.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.UserRole;
import com.sparksys.oauth.interfaces.dto.role.RoleUserDTO;

/**
 * description: 账号角色绑定 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:02:47
 */
public interface IUserRoleService extends SuperCacheService<UserRole> {

    /**
     * 账号角色绑定
     *
     * @param roleUserDTO
     * @return boolean
     */
    boolean saveAuthRoleUser(RoleUserDTO roleUserDTO);

    /**
     * 账号角色解除绑定
     *
     * @param roleUserDTO
     * @return boolean
     */
    boolean deleteAuthRoleUser(RoleUserDTO roleUserDTO);
}
