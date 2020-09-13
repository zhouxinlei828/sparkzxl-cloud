package com.github.sparkzxl.oauth.infrastructure.repository;

import com.github.sparkzxl.oauth.domain.repository.ILoginLogRepository;
import com.github.sparkzxl.oauth.infrastructure.entity.LoginLog;
import com.github.sparkzxl.oauth.infrastructure.mapper.LoginLogMapper;
import org.springframework.stereotype.Repository;

/**
 * description：登录日志 仓储实现类
 *
 * @author zhouxinlei
 * @date 2020/6/17 0017
 */
@Repository
public class LoginLogRepository implements ILoginLogRepository {
    private final LoginLogMapper loginLogMapper;

    public LoginLogRepository(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }
}
