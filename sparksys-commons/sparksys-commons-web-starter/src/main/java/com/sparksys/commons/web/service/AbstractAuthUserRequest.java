package com.sparksys.commons.web.service;

import com.sparksys.commons.core.constant.AuthConstant;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.support.ResponseResultStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * description: 请求用户接口抽象类
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:44:10
 */
@Slf4j
public abstract class AbstractAuthUserRequest {

    /**
     * 根据token获取认证用户
     *
     * @param accessToken
     * @return AuthUser
     * @throws
     * @author zhouxinlei
     * @date 2020-01-03 15:47:42
     */
    public GlobalAuthUser getUserInfo(String accessToken) {
        log.info("accessToken is {}", accessToken);
        String cacheKey = AuthConstant.AUTH_USER.concat(accessToken);
        GlobalAuthUser authUser = getCache(cacheKey);
        ResponseResultStatus.UN_AUTHORIZED.assertNotNull(authUser);
        return authUser;
    }

    /**
     * 缓存中获取用户信息
     *
     * @param key
     * @return AuthUser
     * @throws
     * @author zhouxinlei
     * @date 2020-05-24 10:09:32
     */
    protected abstract GlobalAuthUser getCache(String key);

}
