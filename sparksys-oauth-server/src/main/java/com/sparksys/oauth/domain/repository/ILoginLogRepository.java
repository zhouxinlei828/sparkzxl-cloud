package com.sparksys.oauth.domain.repository;

import com.sparksys.oauth.infrastructure.entity.LoginLog;

/**
 * description：登录日志 仓储类
 *
 * @author zhouxinlei
 * @date 2020/6/17 0017
 */
public interface ILoginLogRepository {

    /**
     * 保存登录日志
     *
     * @param loginLog 日志
     */
    void saveLoginLog(LoginLog loginLog);

}
