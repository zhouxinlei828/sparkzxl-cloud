package com.sparksys.authorization.application.service;

import com.sparksys.commons.core.base.api.result.ApiPageResult;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.authorization.interfaces.dto.user.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserUpdateDTO;
import com.sparksys.commons.security.entity.AdminUserDetails;

import java.util.Set;


/**
 * description: 用户查询 服务类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:23
 */
public interface IAuthUserService {


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

    /**
     * 分页查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    ApiPageResult listByPage(Integer pageNum, Integer pageSize, String name);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    AuthUserDTO getAuthUser(Long id);

    /**
     * 重置密码错误次数
     *
     * @param id
     * @return
     */
    boolean resetPassErrorNum(Long id);

    /**
     * 重置密码错误次数
     *
     * @param account
     * @return
     */
    boolean resetPassErrorNum(String account);

    /**
     * 增加密码错误次数
     *
     * @param id
     * @return boolean
     */
    boolean incrPasswordErrorNum(Long id);

    /**
     * 增加密码错误次数
     *
     * @param account
     * @return boolean
     */
    boolean incrPasswordErrorNum(String account);

    /**
     * 获取用户权限集
     *
     * @param id
     * @return Set<String>
     */
    Set<String> getAuthUserPermissions(Long id);

    /**
     * 获取授权用户信息
     *
     * @param account
     * @return
     */
    AdminUserDetails getAdminUserDetails(String account);
}
