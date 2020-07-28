package com.sparksys.oauth.application.service;


import com.github.pagehelper.PageInfo;
import com.sparksys.database.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.AuthRole;
import com.sparksys.oauth.interfaces.dto.role.*;

/**
 * description: 角色 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleService extends SuperCacheService<AuthRole> {

    /**
     * 根据角色名称模糊查询角色列表
     *
     * @param authRolePageDTO 角色分页查询对象
     * @return PageInfo<AuthRole>
     */
    PageInfo<AuthRole> listByPage(AuthRolePageDTO authRolePageDTO);

    /**
     * 查询角色信息
     *
     * @param id
     * @return
     */
    AuthRoleDTO getAuthRole(Long id);

    /**
     * 保存角色信息
     *
     * @param userId
     * @param authRoleSaveDTO
     * @return
     */
    boolean saveAuthRole(Long userId, AuthRoleSaveDTO authRoleSaveDTO);

    /**
     * 更新角色信息
     *
     * @param userId
     * @param authRoleUpdateDTO
     * @return
     */
    boolean updateAuthRole(Long userId, AuthRoleUpdateDTO authRoleUpdateDTO);

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
