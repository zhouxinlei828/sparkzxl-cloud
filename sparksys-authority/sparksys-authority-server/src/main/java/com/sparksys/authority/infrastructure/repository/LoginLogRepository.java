package com.sparksys.authority.infrastructure.repository;

import com.sparksys.authority.domain.repository.ILoginLogRepository;
import com.sparksys.authority.infrastructure.entity.LoginLog;
import com.sparksys.authority.infrastructure.entity.LoginLogCount;
import com.sparksys.authority.infrastructure.mapper.LoginLogMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<LoginLogCount> findLastTenDaysVisitCount(LocalDate tenDays, String account) {
        return loginLogMapper.findLastTenDaysVisitCount(tenDays, account);
    }

    @Override
    public List<LoginLogCount> findByBrowser() {
        return loginLogMapper.findByBrowser();
    }

    @Override
    public List<LoginLogCount> findByOperatingSystem() {
        return loginLogMapper.findByOperatingSystem();
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return loginLogMapper.clearLog(clearBeforeTime, clearBeforeNum);
    }
}
