package com.github.sparkzxl.oauth.application.service;


import com.github.sparkzxl.core.entity.UserAgentEntity;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.oauth.infrastructure.entity.LoginLog;

/**
 * description：系统日志 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-17 11:33:15
 */
public interface ILoginLogService extends SuperCacheService<LoginLog> {

    /**
     * 记录登录日志
     *
     * @param userId          用户id
     * @param account         账号
     * @param userAgentEntity 用户代理
     * @param description     登陆描述消息
     */
    void save(Long userId, String account, UserAgentEntity userAgentEntity, String description);

}
