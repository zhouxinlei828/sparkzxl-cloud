package com.sparksys.authorization.application.service;


import com.sparksys.authorization.infrastructure.entity.LoginLogCount;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description：系统日志 服务类
 *
 * @author zhouxinlei
 * @date  2020-06-17 11:33:15
 */
public interface ILoginLogService {

    /**
     * 记录登录日志
     *
     * @param userId      用户id
     * @param account     账号
     * @param ua          浏览器
     * @param ip          客户端IP
     * @param location    客户端地址
     * @param description 登陆描述消息
     */
    boolean save(Long userId, String account, String ua, String ip, String location, String description);

    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long findTotalVisitCount();

    /**
     * 获取系统今日访问次数
     *
     * @return Long
     */
    Long findTodayVisitCount();

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    Long findTodayIp();


    /**
     * 获取系统近十天来的访问记录
     *
     * @param account 账号
     * @return List<LoginLogCount>
     */
    List<LoginLogCount> findLastTenDaysVisitCount(String account);

    /**
     * 按浏览器来统计数量
     *
     * @return List<LoginLogCount>
     */
    List<LoginLogCount> findByBrowser();

    /**
     * 按造作系统内统计数量
     *
     * @return List<LoginLogCount>
     */
    List<LoginLogCount> findByOperatingSystem();

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return boolean
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
