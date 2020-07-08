package com.sparksys.authorization.domain.service;

import cn.hutool.core.util.StrUtil;
import com.sparksys.commons.core.cache.CacheKey;
import com.sparksys.commons.core.cache.CacheProviderService;
import com.sparksys.commons.mybatis.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.ILoginLogService;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import com.sparksys.authorization.domain.repository.ILoginLogRepository;
import com.sparksys.authorization.infrastructure.entity.AuthUser;
import com.sparksys.authorization.infrastructure.entity.LoginLog;
import com.sparksys.authorization.infrastructure.entity.LoginLogCount;
import com.sparksys.authorization.infrastructure.mapper.LoginLogMapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * description：系统日志 服务实现类
 *
 * @author zhouxinlei
 * @date 2020/6/17 0017
 */
@Service
public class LoginLogServiceImpl extends AbstractSuperCacheServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Autowired
    private IAuthUserRepository authUserRepository;

    @Autowired
    private ILoginLogRepository loginLogRepository;

    @Autowired
    @Qualifier("redisCache")
    private CacheProviderService cacheProviderService;


    private final static String[] BROWSER = new String[]{
            "Chrome", "Firefox", "Microsoft Edge", "Safari", "Opera"
    };
    private final static String[] OPERATING_SYSTEM = new String[]{
            "Android", "Linux", "Mac OS X", "Ubuntu", "Windows 10", "Windows 8", "Windows 7", "Windows XP", "Windows Vista"
    };

    private static String simplifyOperatingSystem(String operatingSystem) {
        for (String b : OPERATING_SYSTEM) {
            if (StrUtil.containsIgnoreCase(operatingSystem, b)) {
                return b;
            }
        }
        return operatingSystem;
    }

    private static String simplifyBrowser(String browser) {
        for (String b : BROWSER) {
            if (StrUtil.containsIgnoreCase(browser, b)) {
                return b;
            }
        }
        return browser;
    }

    @Override
    public void save(Long userId, String account, String ua, String ip, String location, String description) {
        AuthUser authUser;
        if (userId != null) {
            authUser = authUserRepository.selectById(userId);
        } else {
            authUser = authUserRepository.selectByAccount(account);
        }
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        Version browserVersion = userAgent.getBrowserVersion();
        String version = browserVersion != null ? browserVersion.getVersion() : null;
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        LoginLog loginLog = LoginLog.builder()
                .location(location)
                .loginDate(LocalDate.now())
                .description(description)
                .requestIp(ip).ua(ua)
                .browser(simplifyBrowser(browser.getName()))
                .browserVersion(version)
                .operatingSystem(simplifyOperatingSystem(operatingSystem.getName()))
                .build();
        if (authUser != null) {
            loginLog.setAccount(authUser.getAccount()).setUserId(authUser.getId()).setUserName(authUser.getName())
                    .setCreateUser(authUser.getId());
        }
        loginLogRepository.saveLoginLog(loginLog);
        LocalDate now = LocalDate.now();
        LocalDate tenDays = now.plusDays(-9);
        cacheProviderService.remove(CacheKey.LOGIN_LOG_TOTAL);
        cacheProviderService.remove(CacheKey.buildKey(CacheKey.LOGIN_LOG_TODAY, now));
        cacheProviderService.remove(CacheKey.buildKey(CacheKey.LOGIN_LOG_TODAY_IP, now));
        cacheProviderService.remove(CacheKey.buildKey(CacheKey.LOGIN_LOG_BROWSER));
        cacheProviderService.remove(CacheKey.buildKey(CacheKey.LOGIN_LOG_SYSTEM));
        if (authUser != null) {
            cacheProviderService.remove(CacheKey.buildKey(CacheKey.LOGIN_LOG_TEN_DAY, tenDays, account));
        }
    }

    @Override
    public Long findTotalVisitCount() {
        return cacheProviderService.get(CacheKey.LOGIN_LOG_TOTAL);
    }

    @Override
    public Long findTodayVisitCount() {
        LocalDate now = LocalDate.now();
        return cacheProviderService.get(CacheKey.buildKey(CacheKey.LOGIN_LOG_TODAY, now));
    }

    @Override
    public Long findTodayIp() {
        LocalDate now = LocalDate.now();
        return cacheProviderService.get(CacheKey.buildKey(CacheKey.LOGIN_LOG_TODAY_IP, now));
    }

    @Override
    public List<LoginLogCount> findLastTenDaysVisitCount(String account) {
        LocalDate tenDays = LocalDate.now().plusDays(-9);
        return cacheProviderService.get(CacheKey.buildKey(CacheKey.LOGIN_LOG_TEN_DAY, tenDays, account),
                (key) -> loginLogRepository.findLastTenDaysVisitCount(tenDays, account));
    }

    @Override
    public List<LoginLogCount> findByBrowser() {
        return cacheProviderService.get(CacheKey.buildKey(CacheKey.LOGIN_LOG_BROWSER),
                (key) -> loginLogRepository.findByBrowser());
    }

    @Override
    public List<LoginLogCount> findByOperatingSystem() {
        return cacheProviderService.get(CacheKey.buildKey(CacheKey.LOGIN_LOG_SYSTEM),
                (key) -> loginLogRepository.findByOperatingSystem());
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return loginLogRepository.clearLog(clearBeforeTime, clearBeforeNum);
    }

    @Override
    protected String getRegion() {
        return null;
    }
}
