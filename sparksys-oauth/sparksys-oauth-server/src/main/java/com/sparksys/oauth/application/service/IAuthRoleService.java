package com.sparksys.oauth.application.service;


import com.github.pagehelper.PageInfo;
import com.sparksys.database.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.AuthRole;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleUpdateDTO;
import com.sparksys.oauth.interfaces.dto.role.RoleUserDTO;

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
     * @param pageNum
     * @param pageSize
     * @param name     角色名称
     * @return PageInfo<AuthRole>
     */
    PageInfo<AuthRole> listByPage(Integer pageNum, Integer pageSize, String name);

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
     * @param id
     * @return
     */
    boolean deleteAuthRole(Long id);

    boolean updateAuthRoleStatus(Long userId, Long roleId, Boolean status);
}
