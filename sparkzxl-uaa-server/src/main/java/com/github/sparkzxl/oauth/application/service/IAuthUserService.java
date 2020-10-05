package com.github.sparkzxl.oauth.application.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.oauth.infrastructure.entity.LoginAuthUser;
import com.github.sparkzxl.oauth.infrastructure.entity.UserActivities;
import com.github.sparkzxl.oauth.infrastructure.entity.UserInfo;
import com.github.sparkzxl.oauth.infrastructure.entity.UserNotices;
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserDTO;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.oauth.entity.AuthUserDetail;
import com.github.sparkzxl.oauth.infrastructure.entity.AuthUser;
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserPageDTO;

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
     * @param username 账户
     * @return AuthUserDetail
     */
    AuthUserDetail getAuthUserDetail(String username);

    /**
     * 获取全局用户信息
     *
     * @param username 用户账户
     * @return AuthUserInfo<Long>
     */
    AuthUserInfo<Long> getAuthUserInfo(String username);


    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return AuthUserDTO
     */
    AuthUserDTO getAuthUser(Long id);

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

    /**
     * 获取用户分页
     * @param authUserPageDTO 分页入参
     * @return PageInfo<AuthUser>
     */
    PageInfo<AuthUser> getAuthUserPage(AuthUserPageDTO authUserPageDTO);
}
