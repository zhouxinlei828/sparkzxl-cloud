package com.github.sparkzxl.test.application.service;

import com.github.sparkzxl.test.infrastructure.entity.AuthUser;
import com.github.sparkzxl.database.base.service.SuperCacheService;



/**
 * description: 用户查询 服务类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:23
 */
public interface IAuthUserService extends SuperCacheService<AuthUser> {

    /**
     * 测试分布式事务
     * @param authUser
     * @return
     */
    boolean testSeataTx(AuthUser authUser);
}
