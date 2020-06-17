package com.sparksys.authorization.infrastructure.repository;

import com.sparksys.authorization.domain.repository.ILoginLogRepository;
import com.sparksys.authorization.infrastructure.entity.LoginLog;
import com.sparksys.authorization.infrastructure.entity.LoginLogCount;
import com.sparksys.authorization.infrastructure.mapper.LoginLogMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
