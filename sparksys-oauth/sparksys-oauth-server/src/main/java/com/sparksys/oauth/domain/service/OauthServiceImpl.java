package com.sparksys.oauth.domain.service;

import com.sparksys.commons.core.constant.CacheKey;
import com.sparksys.commons.core.repository.CacheRepository;

import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.support.ResponseResultStatus;
import com.sparksys.commons.core.utils.SpringContextUtils;
import com.sparksys.commons.oauth.enums.GrantTypeEnum;
import com.sparksys.commons.oauth.service.OauthService;
import com.sparksys.commons.security.entity.AuthUserDetail;
import com.sparksys.commons.security.event.LoginEvent;
import com.sparksys.commons.security.entity.LoginStatus;
import com.sparksys.oauth.application.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.Map;

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
    private CacheRepository cacheRepository;
    @Autowired
    private IAuthUserService authUserService;

    @Override
    public OAuth2AccessToken getAccessToken(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.getAccessToken(principal, parameters);
        return loginEventAndBack(parameters, oAuth2AccessTokenResponseEntity);
    }

    private OAuth2AccessToken loginEventAndBack(Map<String, String> parameters, ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity) {
        String grantType = parameters.get("grant_type");
        if (!oAuth2AccessTokenResponseEntity.getStatusCode().isError()) {
            OAuth2AccessToken oAuth2AccessToken = oAuth2AccessTokenResponseEntity.getBody();
            if (GrantTypeEnum.PASSWORD.getType().equals(grantType)) {
                String username = parameters.get("username");
                assert oAuth2AccessToken != null;
                accessToken(username, oAuth2AccessToken);
                SpringContextUtils.publishEvent(new LoginEvent(LoginStatus.success(username)));
            }
            return oAuth2AccessToken;
        }
        if (GrantTypeEnum.PASSWORD.getType().equals(grantType)) {
            String username = parameters.get("username");
            SpringContextUtils.publishEvent(new LoginEvent(LoginStatus.fail(username, "授权登录失败")));
        }
        ResponseResultStatus.AUTHORIZED_FAIL.newException(oAuth2AccessTokenResponseEntity);
        return null;
    }

    @Override
    public OAuth2AccessToken postAccessToken(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        return loginEventAndBack(parameters, oAuth2AccessTokenResponseEntity);
    }

    /**
     * 设置accessToken缓存
     *
     * @param parameter         账户名
     * @param oAuth2AccessToken 认证token
     * @return void
     */
    private void accessToken(String parameter, OAuth2AccessToken oAuth2AccessToken) {
        AuthUserDetail authUserDetail = authUserService.getAuthUserDetail(parameter);
        GlobalAuthUser globalAuthUser = authUserDetail.getAuthUser();
        String token = oAuth2AccessToken.getValue();
        Long expiresIn = (long) oAuth2AccessToken.getExpiresIn();
        cacheRepository.set(CacheKey.buildKey(CacheKey.AUTH_USER, token), globalAuthUser, expiresIn);
    }

}
