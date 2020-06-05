package com.sparksys.authorization.domain.repository;

import com.sparksys.authorization.infrastructure.po.AuthUserDO;

/**
 * description: 用户仓储接口
 *
 * @Author zhouxinlei
 * @Date 2020-06-05 20:39:15
 */
public interface IAuthUserRepository {

    AuthUserDO selectByUserName(String username);

    AuthUserDO selectByCondition(AuthUserDO authUserDO);
}
