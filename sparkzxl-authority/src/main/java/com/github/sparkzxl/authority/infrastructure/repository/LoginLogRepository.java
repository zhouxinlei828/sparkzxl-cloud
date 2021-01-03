package com.github.sparkzxl.authority.infrastructure.repository;

import com.github.sparkzxl.authority.domain.repository.ILoginLogRepository;
import com.github.sparkzxl.authority.infrastructure.entity.LoginLog;
import com.github.sparkzxl.authority.infrastructure.entity.LoginLogCount;
import com.github.sparkzxl.authority.infrastructure.mapper.LoginLogMapper;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Repository
public class LoginLogRepository implements ILoginLogRepository {

    private final LoginLogMapper loginLogMapper;

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
