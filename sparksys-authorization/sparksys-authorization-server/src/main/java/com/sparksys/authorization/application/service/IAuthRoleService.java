package com.sparksys.authorization.application.service;


import com.github.pagehelper.PageInfo;
import com.sparksys.core.entity.GlobalAuthUser;
import com.sparksys.database.service.SuperCacheService;
import com.sparksys.authorization.infrastructure.entity.AuthRole;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleUpdateDTO;

/**
 * description: 角色 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleService extends SuperCacheService<AuthRole> {

    /**
     * 查询角色列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
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
     * @param authUser
     * @param authRoleSaveDTO
     * @return
     */
    boolean saveAuthRole(GlobalAuthUser authUser, AuthRoleSaveDTO authRoleSaveDTO);

    /**
     * 更新角色信息
     *
     * @param authUser
     * @param authRoleUpdateDTO
     * @return
     */
    boolean updateAuthRole(GlobalAuthUser authUser, AuthRoleUpdateDTO authRoleUpdateDTO);

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    boolean deleteAuthRole(Long id);

    boolean updateAuthRoleStatus(Long userId, Long roleId, Boolean status);
}
