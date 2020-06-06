package com.sparksys.authorization.application.service;

import com.sparksys.authorization.interfaces.dto.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserUpdateDTO;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.security.entity.AdminUserDetails;

/**
 * description: 用户查询 服务类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:23
 */
public interface IAuthUserService {

    /**
     * 获取授权认证用户
     *
     * @param username 用户名
     * @return
     */
    AdminUserDetails getAdminUserDetails(String username);

    /**
     * 保存用户信息
     *
     * @param authUser
     * @param authUserSaveDTO
     * @return
     */
    boolean saveAuthUser(AuthUser authUser, AuthUserSaveDTO authUserSaveDTO);

    /**
     * 修改用户信息
     *
     * @param authUser
     * @param authUserUpdateDTO
     * @return
     */
    boolean updateAuthUser(AuthUser authUser, AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    boolean deleteAuthUser(Long id);

    /**
     * 修改用户账号状态
     *
     * @param authUser
     * @param authUserStatusDTO
     * @return
     */
    boolean updateAuthUserStatus(AuthUser authUser, AuthUserStatusDTO authUserStatusDTO);
}
