package com.github.sparkzxl.oauth.domain.service;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.sparkzxl.oauth.application.service.IAuthUserService;
import com.google.common.collect.Maps;
import com.github.sparkzxl.cache.template.CacheTemplate;
import com.github.sparkzxl.core.constant.BaseContextConstant;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.core.spring.SpringContextUtils;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.core.utils.KeyUtils;
import com.github.sparkzxl.oauth.entity.AuthUserDetail;
import com.github.sparkzxl.oauth.entity.AuthorizationRequest;
import com.github.sparkzxl.oauth.entity.LoginStatus;
import com.github.sparkzxl.oauth.enums.GrantTypeEnum;
import com.github.sparkzxl.oauth.event.LoginEvent;
import com.github.sparkzxl.oauth.service.OauthService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * description：授权登录 服务实现类
 *
 * @author： zhouxinlei
 * @date： 2020-06-24 14:50:40
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private CacheTemplate cacheTemplate;
    @Autowired
    private IAuthUserService authUserService;

    @SneakyThrows
    @Override
    public OAuth2AccessToken getAccessToken(Principal principal, AuthorizationRequest authorizationRequest) {
        Map<String, String> parameters = builderAccessTokenParameters(authorizationRequest);
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.getAccessToken(principal, parameters);
        return loginEventAndBack(authorizationRequest, oAuth2AccessTokenResponseEntity);
    }

    private OAuth2AccessToken loginEventAndBack(AuthorizationRequest authorizationRequest, ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity) {
        String grantType = authorizationRequest.getGrantType();
        if (!oAuth2AccessTokenResponseEntity.getStatusCode().isError()) {
            OAuth2AccessToken oAuth2AccessToken = oAuth2AccessTokenResponseEntity.getBody();
            if (GrantTypeEnum.PASSWORD.getType().equals(grantType)) {
                String username = authorizationRequest.getUsername();
                assert oAuth2AccessToken != null;
                accessToken(username, oAuth2AccessToken);
                SpringContextUtils.publishEvent(new LoginEvent(LoginStatus.success(null, username)));
            }
            return oAuth2AccessToken;
        }
        if (GrantTypeEnum.PASSWORD.getType().equals(grantType)) {
            SpringContextUtils.publishEvent(new LoginEvent(LoginStatus.fail(null, authorizationRequest.getUsername(), "授权登录失败")));
        }
        ResponseResultStatus.AUTHORIZED_FAIL.newException(oAuth2AccessTokenResponseEntity);
        return null;
    }

    @SneakyThrows
    @Override
    public OAuth2AccessToken postAccessToken(Principal principal, AuthorizationRequest authorizationRequest) {
        Map<String, String> parameters = builderAccessTokenParameters(authorizationRequest);
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        return loginEventAndBack(authorizationRequest, oAuth2AccessTokenResponseEntity);
    }

    /**
     * 设置accessToken缓存
     *
     * @param username          账户名
     * @param oAuth2AccessToken 认证token
     */
    private void accessToken(String username, OAuth2AccessToken oAuth2AccessToken) {
        AuthUserInfo<Long> authUserInfo = authUserService.getAuthUserInfo(username);
        log.info("AuthUserInfo json is {}", JSONUtil.toJsonPrettyStr(authUserInfo));
        String buildKey = KeyUtils.buildKey(BaseContextConstant.AUTH_USER, oAuth2AccessToken.getValue());
        cacheTemplate.set(buildKey, authUserInfo, (long) oAuth2AccessToken.getExpiresIn());
    }

    /**
     * 构建认证请求参数
     *
     * @param authorizationRequest 认证请求参数
     * @return Map<String, String>
     */
    private Map<String, String> builderAccessTokenParameters(AuthorizationRequest authorizationRequest) {
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("grant_type", authorizationRequest.getGrantType());
        parameters.put("scope", authorizationRequest.getScope());
        Optional.ofNullable(authorizationRequest.getCode()).ifPresent(value -> parameters.put("code", value));
        Optional.ofNullable(authorizationRequest.getClientId()).ifPresent(value -> parameters.put("client_id", value));
        Optional.ofNullable(authorizationRequest.getRedirectUri()).ifPresent(value -> parameters.put("redirect_uri", value));
        Optional.ofNullable(authorizationRequest.getRefreshToken()).ifPresent(value -> parameters.put("refresh_token", value));
        Optional.ofNullable(authorizationRequest.getUsername()).ifPresent(value -> parameters.put("username", value));
        Optional.ofNullable(authorizationRequest.getPassword()).ifPresent(value -> parameters.put("password", value));
        return parameters;
    }

}
