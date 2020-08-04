package com.sparksys.oauth.domain.service;

import cn.hutool.core.util.StrUtil;
import com.sparksys.core.entity.UserAgentEntity;
import com.sparksys.core.utils.KeyUtils;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.application.service.ILoginLogService;
import com.sparksys.oauth.domain.repository.IAuthUserRepository;
import com.sparksys.oauth.domain.repository.ILoginLogRepository;
import com.sparksys.oauth.infrastructure.constant.CacheConstant;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.entity.LoginLog;
import com.sparksys.oauth.infrastructure.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    public void save(Long userId, String account, UserAgentEntity userAgentEntity, String description) {
        AuthUser authUser;
        if (userId != null) {
            authUser = authUserRepository.selectById(userId);
        } else {
            authUser = authUserRepository.selectByAccount(account);
        }
        LoginLog loginLog = LoginLog.builder()
                .location(userAgentEntity.getLocation())
                .loginDate(LocalDate.now())
                .description(description)
                .requestIp(userAgentEntity.getRequestIp()).ua(userAgentEntity.getUa())
                .browser(userAgentEntity.getBrowser())
                .browserVersion(userAgentEntity.getBrowserVersion())
                .operatingSystem(userAgentEntity.getOperatingSystem())
                .build();
        if (authUser != null) {
            loginLog.setAccount(authUser.getAccount()).setUserId(authUser.getId()).setUserName(authUser.getName())
                    .setCreateUser(authUser.getId());
        }
        loginLogRepository.saveLoginLog(loginLog);
        LocalDate now = LocalDate.now();
        LocalDate tenDays = now.plusDays(-9);
        cacheTemplate.remove(CacheConstant.LOGIN_LOG_TOTAL);
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TODAY, now));
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TODAY_IP, now));
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_BROWSER));
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_SYSTEM));
        if (authUser != null) {
            cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TEN_DAY, tenDays, account));
        }
    }

    @Override
    protected String getRegion() {
        return null;
    }
}
