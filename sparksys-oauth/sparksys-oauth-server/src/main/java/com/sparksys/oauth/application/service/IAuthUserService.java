package com.sparksys.oauth.application.service;

import com.github.pagehelper.PageInfo;
import com.sparksys.database.service.SuperCacheService;
import com.sparksys.security.entity.AuthUserDetail;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.interfaces.dto.user.AuthUserDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserUpdateDTO;

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
     * @return
     */
    boolean saveAuthUser(Long contextUserId, AuthUserSaveDTO authUserSaveDTO);

    /**
     * 修改用户信息
     *
     * @param contextUserId     当前登录用户id
     * @param authUserUpdateDTO
     * @return
     */
    boolean updateAuthUser(Long contextUserId, AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return
     */
    boolean deleteAuthUser(Long id);

    /**
     * 修改用户账号状态
     *
     * @param contextUserId     当前登录用户id
     * @param authUserStatusDTO
     * @return
     */
    boolean updateAuthUserStatus(Long contextUserId, AuthUserStatusDTO authUserStatusDTO);

    /**
     * 分页查询用户列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     * @param name     姓名
     * @return PageInfo<AuthUserDTO>
     */
    PageInfo<AuthUserDTO> listByPage(Integer pageNum, Integer pageSize, String name);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return
     */
    AuthUserDTO getAuthUser(Long id);

    /**
     * 重置密码错误次数
     *
     * @param id 用户id
     * @return boolean
     */
    boolean resetPassErrorNum(Long id);

    /**
     * 重置密码错误次数
     *
     * @param account 账户
     * @return
     */
    boolean resetPassErrorNum(String account);

    /**
     * 增加密码错误次数
     *
     * @param id 用户id
     * @return boolean
     */
    boolean incrPasswordErrorNum(Long id);

    /**
     * 增加密码错误次数
     *
     * @param account 账户
     * @return boolean
     */
    boolean incrPasswordErrorNum(String account);

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
}
