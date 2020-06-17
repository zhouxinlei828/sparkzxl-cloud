package com.sparksys.authorization.domain.repository;

import com.sparksys.authorization.infrastructure.entity.LoginLog;
import com.sparksys.authorization.infrastructure.entity.LoginLogCount;

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
public interface ILoginLogRepository {

    /**
     * 保存登录日志
     *
     * @param loginLog
     * @return boolean
     */
    boolean saveLoginLog(LoginLog loginLog);

    /**
     * 获取系统近十天来的访问记录
     *
     * @param tenDays 10天前
     * @param account 用户账号
     * @return List<LoginLogCount>
     */
    List<LoginLogCount> findLastTenDaysVisitCount(LocalDate tenDays, String account);

    /**
     * 按浏览器来统计数量
     *
     * @return List<LoginLogCount>
     */
    List<LoginLogCount> findByBrowser();

    /**
     * 按操作系统内统计数量
     *
     * @return List<LoginLogCount>
     */
    List<LoginLogCount> findByOperatingSystem();

    /**
     * 清理日志
     *
     * @param clearBeforeTime
     * @param clearBeforeNum
     * @return
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
