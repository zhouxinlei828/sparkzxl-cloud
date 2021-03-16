package com.github.sparkzxl.authorization.infrastructure.security.logout;

import com.github.sparkzxl.core.base.ResponseResultUtils;
import com.github.sparkzxl.core.context.BaseContextConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * description: 退出登录成功处理
 *
 * @author zhouxinlei
 */
@Slf4j
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("token");
        log.info("退出登录成功：{}", token);
        token = StringUtils.removeStartIgnoreCase(token, BaseContextConstants.BEARER_TOKEN);
        if (token != null) {
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
            if (accessToken != null) {
                tokenStore.removeAccessToken(accessToken);
                tokenStore.removeRefreshToken(accessToken.getRefreshToken());
            }
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
        }
        ResponseResultUtils.writeResponseOutMsg(httpServletResponse, 200, "退出登录成功", true);
    }
}
