package com.sparksys.oauth.application.event.listener;

import com.sparksys.oauth.application.service.IAuthUserService;
import com.sparksys.oauth.application.service.ILoginLogService;
import com.sparksys.commons.security.event.model.LoginEvent;
import com.sparksys.commons.security.event.model.LoginStatusDTO;
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
        LoginStatusDTO loginStatus = (LoginStatusDTO) event.getSource();
        if (LoginStatusDTO.Type.SUCCESS == loginStatus.getType()) {
            // 重置错误次数 和 最后登录时间
            if (loginStatus.getId() == null) {
                authUserService.resetPassErrorNum(loginStatus.getAccount());
            } else {
                authUserService.resetPassErrorNum(loginStatus.getId());
            }
        } else if (LoginStatusDTO.Type.PWD_ERROR == loginStatus.getType()) {
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
