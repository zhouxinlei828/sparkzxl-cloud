package com.sparksys.authorization.application.service;

import com.sparksys.authorization.interfaces.dto.user.AuthUserDTO;
import com.sparksys.database.base.service.SuperCacheService;
import com.sparksys.authorization.infrastructure.entity.AuthUser;
import com.sparksys.authorization.infrastructure.entity.UserActivities;
import com.sparksys.authorization.infrastructure.entity.UserInfo;
import com.sparksys.authorization.infrastructure.entity.UserNotices;

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
     * @return boolean
     */
    boolean deleteOrgId(Long id);
}
