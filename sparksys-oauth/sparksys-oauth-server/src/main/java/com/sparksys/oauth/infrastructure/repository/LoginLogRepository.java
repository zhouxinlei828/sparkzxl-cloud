package com.sparksys.oauth.infrastructure.repository;

import com.sparksys.oauth.domain.repository.ILoginLogRepository;
import com.sparksys.oauth.infrastructure.entity.LoginLog;
import com.sparksys.oauth.infrastructure.entity.LoginLogCount;
import com.sparksys.oauth.infrastructure.mapper.LoginLogMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/17 0017
 */
@Repository
public class LoginLogRepository implements ILoginLogRepository {
    private final LoginLogMapper loginLogMapper;

    public LoginLogRepository(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    @Override
    public boolean saveLoginLog(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog) == 1;
    }

    @Override
    public List<LoginLogCount> findLastTenDaysVisitCount(LocalDate tenDays, String account) {
        return loginLogMapper.findLastTenDaysVisitCount(tenDays,account);
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
        return loginLogMapper.clearLog(clearBeforeTime,clearBeforeNum);
    }
}
