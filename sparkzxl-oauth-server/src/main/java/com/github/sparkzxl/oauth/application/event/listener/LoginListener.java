package com.github.sparkzxl.oauth.application.event.listener;

import com.github.sparkzxl.oauth.application.service.IAuthUserService;
import com.github.sparkzxl.oauth.application.service.ILoginLogService;
import com.github.sparkzxl.oauth.event.LoginEvent;
import com.github.sparkzxl.oauth.entity.LoginStatus;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Component
@Slf4j
public class LoginListener {

    private final ILoginLogService loginLogService;

    @Async
    @EventListener({LoginEvent.class})
    public void saveSysLog(LoginEvent event) {
        LoginStatus<Long> loginStatus = (LoginStatus) event.getSource();
        loginLogService.save(loginStatus.getId(),
                loginStatus.getAccount(),
                loginStatus.getUserAgentEntity(),
                loginStatus.getDescription());
    }

}
