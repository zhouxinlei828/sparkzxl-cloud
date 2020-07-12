package com.sparksys.authorization.application.service;

import com.github.pagehelper.PageInfo;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.database.service.SuperCacheService;
import com.sparksys.commons.security.entity.AuthUserDetail;
import com.sparksys.authorization.infrastructure.entity.AuthUser;
import com.sparksys.authorization.interfaces.dto.user.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserUpdateDTO;

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
     * @param authUser
     * @param authUserSaveDTO
     * @return
     */
    boolean saveAuthUser(GlobalAuthUser authUser, AuthUserSaveDTO authUserSaveDTO);

    /**
     * 修改用户信息
     *
     * @param authUser
     * @param authUserUpdateDTO
     * @return
     */
    boolean updateAuthUser(GlobalAuthUser authUser, AuthUserUpdateDTO authUserUpdateDTO);

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
     * @param authUser          用户
     * @param authUserStatusDTO 用户状态dto
     * @return
     */
    boolean updateAuthUserStatus(GlobalAuthUser authUser, AuthUserStatusDTO authUserStatusDTO);

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
     * 获取用户信息
     *
     * @param username
     * @return AuthUserDetail
     */
    AuthUserDetail getAuthUserDetail(String username);
}
