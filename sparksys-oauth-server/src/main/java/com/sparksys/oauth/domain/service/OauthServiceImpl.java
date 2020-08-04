package com.sparksys.oauth.domain.service;

import com.google.common.collect.Maps;
import com.sparksys.cache.template.CacheTemplate;

import com.sparksys.core.constant.BaseContextConstants;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.core.spring.SpringContextUtils;
import com.sparksys.core.support.ResponseResultStatus;
import com.sparksys.core.utils.KeyUtils;
import com.sparksys.oauth.entity.AuthorizationRequest;
import com.sparksys.oauth.enums.GrantTypeEnum;
import com.sparksys.oauth.infrastructure.constant.CacheConstant;
import com.sparksys.oauth.service.OauthService;
import com.sparksys.oauth.entity.AuthUserDetail;
import com.sparksys.oauth.event.LoginEvent;
import com.sparksys.oauth.entity.LoginStatus;
import lombok.SneakyThrows;
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
public class OauthServiceImpl implements OauthService {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private CacheTemplate cacheTemplate;
    @Autowired
    @Qualifier("oauthUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public OAuth2AccessToken getAccessToken(Principal principal, AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
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
                SpringContextUtils.publishEvent(new LoginEvent(LoginStatus.success(username)));
            }
            return oAuth2AccessToken;
        }
        if (GrantTypeEnum.PASSWORD.getType().equals(grantType)) {
            SpringContextUtils.publishEvent(new LoginEvent(LoginStatus.fail(authorizationRequest.getUsername(), "授权登录失败")));
        }
        ResponseResultStatus.AUTHORIZED_FAIL.newException(oAuth2AccessTokenResponseEntity);
        return null;
    }

    @Override
    public OAuth2AccessToken postAccessToken(Principal principal, AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
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
        AuthUserDetail authUserDetail = (AuthUserDetail) userDetailsService.loadUserByUsername(username);
        AuthUserInfo authUserInfo = authUserDetail.getAuthUserInfo();
        cacheTemplate.set(KeyUtils.buildKey(BaseContextConstants.AUTH_USER, oAuth2AccessToken.getValue()), authUserInfo, (long) oAuth2AccessToken.getExpiresIn());
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
