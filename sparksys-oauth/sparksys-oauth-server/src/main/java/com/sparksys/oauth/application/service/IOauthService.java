package com.sparksys.oauth.application.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

/**
 * description: 授权登录 服务类
 *
 * @author： zhouxinlei
 * @date： 2020-06-24 14:49:55
 */
public interface IOauthService {

    /**
     * get请求授权登录
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    OAuth2AccessToken getAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException;

    /**
     * POST请求授权登录
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    OAuth2AccessToken postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException;

}
