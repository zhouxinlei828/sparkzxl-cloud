package com.sparksys.oauth.application.service;

import com.sparksys.database.base.service.SuperCacheService;
import com.sparksys.oauth.entity.AuthUserDetail;
import com.sparksys.oauth.infrastructure.entity.AuthUser;

/**
 * description: 用户查询 服务类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:23
 */
public interface IAuthUserService extends SuperCacheService<AuthUser> {

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
}
