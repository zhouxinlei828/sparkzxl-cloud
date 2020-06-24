package com.sparksys.authorization.application.event.listener;

import com.sparksys.commons.security.event.LoginEvent;
import com.sparksys.commons.security.entity.LoginStatus;
import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.application.service.ILoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * description：登录事件监听，用于记录登录日志
 *
 * @author zhouxinlei
 * @date 2020/6/17 0017
 */
@Component
@Slf4j
public class LoginListener {

    private final ILoginLogService loginLogService;
    private final IAuthUserService authUserService;

    public LoginListener(ILoginLogService loginLogService, IAuthUserService authUserService) {
        this.loginLogService = loginLogService;
        this.authUserService = authUserService;
    }

    @Async
    @EventListener({LoginEvent.class})
    public void saveSysLog(LoginEvent event) {
        LoginStatus loginStatus = (LoginStatus) event.getSource();
        if (LoginStatus.Type.SUCCESS == loginStatus.getType()) {
            // 重置错误次数 和 最后登录时间
            if (loginStatus.getId() == null) {
                authUserService.resetPassErrorNum(loginStatus.getAccount());
            } else {
                authUserService.resetPassErrorNum(loginStatus.getId());
            }
        } else if (LoginStatus.Type.PWD_ERROR == loginStatus.getType()) {
            // 密码错误
            if (loginStatus.getId() == null) {
                authUserService.incrPasswordErrorNum(loginStatus.getAccount());
            } else {
                authUserService.incrPasswordErrorNum(loginStatus.getId());
            }
        }
        loginLogService.save(loginStatus.getId(),
                loginStatus.getAccount(),
                loginStatus.getUa(),
                loginStatus.getIp(),
                loginStatus.getLocation(),
                loginStatus.getDescription());
    }

}
