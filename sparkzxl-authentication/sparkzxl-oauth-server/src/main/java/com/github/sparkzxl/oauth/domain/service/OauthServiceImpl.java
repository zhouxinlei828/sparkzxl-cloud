package com.github.sparkzxl.oauth.domain.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.github.sparkzxl.authority.application.service.IAuthUserService;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.cache.template.CacheTemplate;
import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.core.entity.CaptchaInfo;
import com.github.sparkzxl.core.spring.SpringContextUtils;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.core.utils.BuildKeyUtils;
import com.github.sparkzxl.oauth.entity.AuthorizationRequest;
import com.github.sparkzxl.oauth.entity.LoginStatus;
import com.github.sparkzxl.oauth.enums.GrantTypeEnum;
import com.github.sparkzxl.oauth.event.LoginEvent;
import com.github.sparkzxl.oauth.service.OauthService;
import com.google.common.collect.Maps;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.vavr.API;
import io.vavr.control.Option;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.vavr.API.$;
import static io.vavr.API.Case;

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
        SparkZxlExceptionAssert.businessFail(ResponseResultStatus.AUTHORIZED_FAIL);
        return null;
    }

    @SneakyThrows
    @Override
    public OAuth2AccessToken postAccessToken(Principal principal, AuthorizationRequest authorizationRequest) {
        String captcha = authorizationRequest.getCaptchaCode();
        if (StringUtils.isNotEmpty(captcha)) {
            String captchaKey = authorizationRequest.getCaptchaKey();
            checkCaptcha(captchaKey, captcha);
        }
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
        String buildKey = BuildKeyUtils.generateKey(BaseContextConstants.AUTH_USER, oAuth2AccessToken.getValue());
        cacheTemplate.set(buildKey, authUserInfo, (long) oAuth2AccessToken.getExpiresIn(), TimeUnit.SECONDS);
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
        Option.of(authorizationRequest.getCode()).peek(value -> parameters.put("code", value));
        Option.of(authorizationRequest.getClientId()).peek(value -> parameters.put("client_id", value));
        Option.of(authorizationRequest.getRedirectUri()).peek(value -> parameters.put("redirect_uri", value));
        Option.of(authorizationRequest.getRefreshToken()).peek(value -> parameters.put("refresh_token", value));
        Option.of(authorizationRequest.getUsername()).peek(value -> parameters.put("username", value));
        Option.of(authorizationRequest.getPassword()).peek(value -> parameters.put("password", value));
        return parameters;
    }

    @Override
    public CaptchaInfo createCaptcha(String type) {
        if (StrUtil.isBlank(type)) {
            SparkZxlExceptionAssert.businessFail("验证码类型不能为空");
        }
        CaptchaInfo captchaInfo = new CaptchaInfo();
        Captcha captcha;
        captcha = API.Match(type).of(
                Case($("gif"), new GifCaptcha(115, 42, 4)),
                Case($("png"), new SpecCaptcha(115, 42, 4)),
                Case($("chinese"), new ChineseCaptcha(115, 42)),
                Case($(), new ArithmeticCaptcha(115, 42))
        );
        captcha.setCharType(2);
        String simpleUUID = IdUtil.simpleUUID();
        captchaInfo.setKey(simpleUUID);
        captchaInfo.setData(captcha.toBase64());
        cacheTemplate.set(BuildKeyUtils.generateKey(CacheConstant.CAPTCHA, simpleUUID), captcha.text().toLowerCase(), 60L, TimeUnit.SECONDS);
        return captchaInfo;
    }

    @Override
    public boolean checkCaptcha(String key, String value) {
        if (StrUtil.isBlank(value)) {
            SparkZxlExceptionAssert.businessFail(400, "请输入验证码");
        }
        String cacheKey = BuildKeyUtils.generateKey(CacheConstant.CAPTCHA, key);
        String captchaData = cacheTemplate.get(cacheKey);
        if (StringUtils.isEmpty(captchaData)) {
            SparkZxlExceptionAssert.businessFail(400, "验证码已过期");
        }
        if (!StrUtil.equalsIgnoreCase(value, captchaData)) {
            SparkZxlExceptionAssert.businessFail(400, "验证码不正确");
        }
        cacheTemplate.remove(cacheKey);
        return true;
    }
}
