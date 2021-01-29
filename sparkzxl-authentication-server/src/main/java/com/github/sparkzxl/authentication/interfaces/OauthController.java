package com.github.sparkzxl.authentication.interfaces;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "授权管理")
@Slf4j
@RequestMapping("/oauth1")
public class OauthController {

    @Value("${application.frontend_url}")
    private String defaultLoginSuccessUrl;

    @Value("${application.logout_url}")
    private String logoutUrl;

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) throws ServletException {
        req.logout();
        UrlBuilder builder = UrlBuilder.ofHttp(logoutUrl, CharsetUtil.CHARSET_UTF_8);
        builder.addQuery("redirect_uri",defaultLoginSuccessUrl);
        return "redirect:".concat(builder.build());
    }
}
