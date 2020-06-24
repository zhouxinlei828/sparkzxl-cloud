package com.sparksys.oauth.domain.service;

import com.sparksys.commons.core.support.ResponseResultStatus;
import com.sparksys.commons.oauth.enums.GrantTypeEnum;
import com.sparksys.commons.security.event.model.LoginEvent;
import com.sparksys.commons.security.event.model.LoginStatusDTO;
import com.sparksys.commons.web.component.SpringContextUtils;
import com.sparksys.oauth.application.service.IAuthUserService;
import com.sparksys.oauth.application.service.IOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import springfox.documentation.service.GrantType;

import java.security.Principal;
import java.util.Map;

/**
 * description：授权登录 服务实现类
 *
 * @author： zhouxinlei
 * @date： 2020-06-24 14:50:40
 */
@Service
public class OauthServiceImpl implements IOauthService {

    @Autowired
    private TokenEndpoint tokenEndpoint;
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
                SpringContextUtils.publishEvent(new LoginEvent(LoginStatusDTO.success(username)));
            }
            return oAuth2AccessToken;
        }
        if (GrantTypeEnum.PASSWORD.getType().equals(grantType)) {
            String username = parameters.get("username");
            SpringContextUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(username, "授权登录失败")));
        }
        ResponseResultStatus.AUTHORIZED_FAIL.newException(oAuth2AccessTokenResponseEntity);
        return null;
    }

    @Override
    public OAuth2AccessToken postAccessToken(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        return loginEventAndBack(parameters, oAuth2AccessTokenResponseEntity);
    }
}
