package com.sparksys.oauth.application.service;

import com.github.pagehelper.PageInfo;
import com.sparksys.database.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.UserInfo;
import com.sparksys.oauth.infrastructure.entity.UserNotices;
import com.sparksys.oauth.infrastructure.entity.UserActivities;
import com.sparksys.oauth.interfaces.dto.user.*;
import com.sparksys.security.entity.AuthUserDetail;
import com.sparksys.oauth.infrastructure.entity.AuthUser;

import java.util.List;
import java.util.Set;


/**
 * description: 用户查询 服务类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:23
 */
public interface IAuthUserService extends SuperCacheService<AuthUser> {


    /**
     * 保存用户信息
     *
     * @param contextUserId   当前登录用户id
     * @param authUserSaveDTO 保存dto对象
     * @return boolean
     */
    boolean saveAuthUser(Long contextUserId, AuthUserSaveDTO authUserSaveDTO);

    /**
     * 修改用户信息
     *
     * @param contextUserId     当前登录用户id
     * @param authUserUpdateDTO AuthUserUpdateDTO更新对象
     * @return boolean
     */
    boolean updateAuthUser(Long contextUserId, AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return boolean
     */
    boolean deleteAuthUser(Long id);

    /**
     * 修改用户账号状态
     *
     * @param contextUserId     当前登录用户id
     * @param authUserStatusDTO AuthUserStatusDTO状态修改对象
     * @return boolean
     */
    boolean updateAuthUserStatus(Long contextUserId, AuthUserStatusDTO authUserStatusDTO);

    /**
     * 分页查询用户列表
     *
     * @param authUserPageDTO 分页查询参数
     * @return PageInfo<AuthUserDTO>
     */
    PageInfo<AuthUserDTO> listByPage(AuthUserPageDTO authUserPageDTO);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return AuthUserDTO
     */
    AuthUserDTO getAuthUser(Long id);

    /**
     * 重置密码错误次数
     *
     * @param id 用户id
     */
    void resetPassErrorNum(Long id);

    /**
     * 重置密码错误次数
     *
     * @param account 账户
     */
    void resetPassErrorNum(String account);

    /**
     * 增加密码错误次数
     *
     * @param id 用户id
     */
    void incrPasswordErrorNum(Long id);

    /**
     * 增加密码错误次数
     *
     * @param account 账户
     */
    void incrPasswordErrorNum(String account);

    /**
     * 获取用户信息
     *
     * @param username 账户
     * @return AuthUserDetail
     */
    AuthUserDetail getAuthUserDetail(String username);

    /**
     * 获取用户权限集
     *
     * @param id 用户id
     * @return Set<String>
     */
    Set<String> getAuthUserPermissions(Long id);

    /**
     * 获取登录用户
     *
     * @param username 用户名
     * @return UserInfo
     */
    UserInfo getCurrentUser(String username);

    /**
     * 获取用户通知
     *
     * @return List<UserNotices>
     */
    List<UserNotices> getUserNotices();

    /**
     * 获取文章
     *
     * @return
     */
    List<UserActivities> activities();

    /**
     * 更新用户组织
     *
     * @param id 组织id
     * @return boolean
     */
    boolean deleteOrgId(Long id);
}
