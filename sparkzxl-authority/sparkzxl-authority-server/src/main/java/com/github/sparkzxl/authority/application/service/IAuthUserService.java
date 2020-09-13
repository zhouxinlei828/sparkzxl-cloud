package com.github.sparkzxl.authority.application.service;

import com.github.sparkzxl.authority.infrastructure.entity.*;
import com.github.sparkzxl.authority.interfaces.dto.user.AuthUserDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;

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
     */
    void deleteOrgId(Long id);

    /**
     * 删除用户关联信息
     *
     * @param ids 用户id列表
     */
    void deleteUserRelation(List<Long> ids);

    /**
     * 查询登录用户信息
     *
     * @param id 用户id
     * @return LoginAuthUser
     */
    LoginAuthUser getLoginAuthUser(Long id);
}
