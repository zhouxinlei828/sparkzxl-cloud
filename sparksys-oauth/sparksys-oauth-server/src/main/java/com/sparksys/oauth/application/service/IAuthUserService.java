package com.sparksys.oauth.application.service;

import com.sparksys.oauth.infrastructure.entity.AuthUserDetail;
import com.sparksys.oauth.interfaces.dto.user.AuthUserDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserUpdateDTO;
import com.sparksys.commons.core.base.api.result.ApiPageResult;
import com.sparksys.commons.core.entity.AuthUser;

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

    boolean resetPassErrorNum(Long id);

    boolean incrPasswordErrorNumById(Long id);

    AuthUserDetail getAuthUserDetail(String username);
}
