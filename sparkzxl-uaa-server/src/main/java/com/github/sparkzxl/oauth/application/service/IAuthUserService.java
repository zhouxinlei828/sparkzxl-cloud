package com.github.sparkzxl.oauth.application.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.oauth.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.oauth.domain.model.vo.AuthUserBasicVO;
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
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserUpdateDTO;

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
    AuthUserDetail<Long> getAuthUserDetail(String username);

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
     *
     * @param authUserPageDTO 分页入参
     * @return PageInfo<AuthUser>
     */
    PageInfo<AuthUser> getAuthUserPage(AuthUserPageDTO authUserPageDTO);

    /**
     * 保存用户信息
     *
     * @param authUserSaveDTO AuthUserSaveDTO保存对象
     * @return boolean
     */
    boolean saveAuthUser(AuthUserSaveDTO authUserSaveDTO);

    /**
     * 修改用户信息
     *
     * @param authUserUpdateDTO AuthUserSaveDTO修改对象
     * @return boolean
     */
    boolean updateAuthUser(AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * 批量更新用户组织
     *
     * @param orgIds 组织ids
     */
    void deleteOrgIds(List<Long> orgIds);

    /**
     * 生成仿真数据
     *
     * @return boolean
     */
    boolean mockUserData();

    /**
     * 获取登录用户全量信息
     * @return AuthUserBasicVO
     */
    AuthUserBasicVO getAuthUserBasicInfo();

    /**
     * 用户菜单
     * @return List<MenuBasicInfo>
     */
    List<MenuBasicInfo> routers();
}
