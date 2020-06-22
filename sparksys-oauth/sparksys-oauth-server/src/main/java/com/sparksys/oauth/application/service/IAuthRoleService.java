package com.sparksys.oauth.application.service;


import com.sparksys.oauth.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleUpdateDTO;
import com.sparksys.commons.core.base.api.result.ApiPageResult;
import com.sparksys.commons.core.entity.AuthUser;

/**
 * description: 角色 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleService {

    /**
     * 查询角色列表
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    ApiPageResult listByPage(Integer pageNum, Integer pageSize, String name);

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
    boolean saveAuthRole(AuthUser authUser, AuthRoleSaveDTO authRoleSaveDTO);

    /**
     * 更新角色信息
     *
     * @param authUser
     * @param authRoleUpdateDTO
     * @return
     */
    boolean updateAuthRole(AuthUser authUser, AuthRoleUpdateDTO authRoleUpdateDTO);

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    boolean deleteAuthRole(Long id);

    boolean updateAuthRoleStatus(Long userId, Long roleId, Boolean status);
}
