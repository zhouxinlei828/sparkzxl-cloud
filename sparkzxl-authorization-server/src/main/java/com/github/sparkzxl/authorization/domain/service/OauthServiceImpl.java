package com.github.sparkzxl.authorization.domain.service;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlPath;
import cn.hutool.core.util.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.sparkzxl.authorization.application.service.IAuthUserService;
import com.github.sparkzxl.authorization.application.service.ITenantInfoService;
import com.github.sparkzxl.authorization.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.cache.template.CacheTemplate;
import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.core.entity.CaptchaInfo;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.core.utils.BuildKeyUtils;
import com.github.sparkzxl.core.utils.ListUtils;
import com.github.sparkzxl.core.utils.RequestContextHolderUtils;
import com.github.sparkzxl.open.entity.AccessTokenInfo;
import com.github.sparkzxl.open.entity.AuthorizationCallBackResponse;
import com.github.sparkzxl.open.entity.AuthorizationRequest;
import com.github.sparkzxl.open.properties.OpenProperties;
import com.github.sparkzxl.open.service.OauthService;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.CustomTokenGrantService;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    @Autowired
    private ITenantInfoService tenantService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private OpenProperties openProperties;
    @Autowired
    private CustomTokenGrantService customTokenGrantService;
    @Autowired
    private TokenStore tokenStore;

    @SneakyThrows
    @Override
    public OAuth2AccessToken getAccessToken(Principal principal, AuthorizationRequest authorizationRequest) {
        checkTenantCode();
        String captcha = authorizationRequest.getCaptchaCode();
        if (StringUtils.isNotEmpty(captcha)) {
            String captchaKey = authorizationRequest.getCaptchaKey();
            checkCaptcha(captchaKey, captcha);
        }
        Map<String, String> parameters = builderAccessTokenParameters(authorizationRequest);
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.getAccessToken(principal, parameters);
        return loginEventAndBack(authorizationRequest, oAuth2AccessTokenResponseEntity);
    }

    private OAuth2AccessToken loginEventAndBack(AuthorizationRequest authorizationRequest, ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity) {
        String grantType = authorizationRequest.getGrantType();
        if (!oAuth2AccessTokenResponseEntity.getStatusCode().isError()) {
            OAuth2AccessToken oAuth2AccessToken = oAuth2AccessTokenResponseEntity.getBody();
            assert oAuth2AccessToken != null;
            buildGlobalUserInfo(oAuth2AccessToken);
            return oAuth2AccessToken;
        }
        SparkZxlExceptionAssert.businessFail(ResponseResultStatus.AUTHORIZED_FAIL);
        return null;
    }

    @SneakyThrows
    @Override
    public OAuth2AccessToken postAccessToken(Principal principal, AuthorizationRequest authorizationRequest) {
        checkTenantCode();
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
     * @param oAuth2AccessToken 认证token
     */
    private void buildGlobalUserInfo(OAuth2AccessToken oAuth2AccessToken) {
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        String username = (String) additionalInformation.get("username");
        AuthUserInfo<Long> authUserInfo = authUserService.getAuthUserInfo(username);
        String authUserInfoKey = BuildKeyUtils.generateKey(BaseContextConstants.AUTH_USER_TOKEN, authUserInfo.getId());
        redisTemplate.opsForHash().put(authUserInfoKey, oAuth2AccessToken.getValue(), authUserInfo);
        redisTemplate.expire(authUserInfoKey, oAuth2AccessToken.getExpiresIn(), TimeUnit.SECONDS);
    }

    private void checkTenantCode() {
        boolean success = true;
        String tenantCode = RequestContextHolderUtils.getHeader(BaseContextConstants.JWT_KEY_TENANT);
        if (StringUtils.isNotEmpty(tenantCode)) {
            int count = tenantService.count(new LambdaQueryWrapper<TenantInfo>().eq(TenantInfo::getCode, tenantCode));
            success = count > 0;
        }
        if (!success) {
            SparkZxlExceptionAssert.businessFail("该租户不存在");
        }
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
        Option.of(authorizationRequest.getClientSecret()).peek(value -> parameters.put("client_secret", value));
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

    @Override
    public String getAuthorizeUrl(String clientId, String frontUrl) {
        String state = RandomUtil.randomString(6);
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(StringUtils.isNotEmpty(clientId) ? clientId
                : openProperties.getAppId());
        HttpServletRequest request = RequestContextHolderUtils.getRequest();
        List<String> redirectUriList = ListUtils.setToList(clientDetails.getRegisteredRedirectUri());
        String authorizeUrl = UrlBuilder.create()
                .setScheme(request.getScheme())
                .setHost(request.getServerName())
                .setPort(request.getServerPort())
                .addPath("/oauth/authorize")
                .addQuery("client_id", clientDetails.getClientId())
                .addQuery("redirect_uri", redirectUriList.get(0))
                .addQuery("response_type", "code")
                .addQuery("state", state)
                .build();
        String referer = request.getHeader("Referer");
        UrlBuilder builder = UrlBuilder.ofHttp(referer, CharsetUtil.CHARSET_UTF_8);
        builder.setPath(UrlPath.of("jump", StandardCharsets.UTF_8));
        String frontStateKey = BuildKeyUtils.generateKey(CacheConstant.FRONT_STATE, state);
        cacheTemplate.set(frontStateKey, builder.build(), 5L, TimeUnit.MINUTES);
        return EscapeUtil.safeUnescape(authorizeUrl);
    }

    @SneakyThrows
    @Override
    public AuthorizationCallBackResponse callBack(String authorizationCode, String loginState) {
        String frontStateKey = BuildKeyUtils.generateKey(CacheConstant.FRONT_STATE, loginState);
        String frontUrl = cacheTemplate.get(frontStateKey);
        if (StringUtils.isEmpty(frontUrl)) {
            return null;
        }
        cacheTemplate.remove(frontStateKey);
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(openProperties.getAppId());
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("grant_type", "authorization_code");
        Set<String> detailsScope = clientDetails.getScope();
        String scope = String.join(",", detailsScope);
        parameters.put("scope", scope);
        parameters.put("code", authorizationCode);
        parameters.put("client_id", clientDetails.getClientId());
        parameters.put("client_secret", clientDetails.getClientSecret());
        List<String> redirectUriList = ListUtils.setToList(clientDetails.getRegisteredRedirectUri());
        parameters.put("redirect_uri", redirectUriList.get(0));
        DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) customTokenGrantService.getAccessToken(parameters);
        buildGlobalUserInfo(oAuth2AccessToken);
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();

        AuthorizationCallBackResponse authorizationCallBackResponse = new AuthorizationCallBackResponse();
        authorizationCallBackResponse.setFrontUrl(frontUrl);
        String tokenState = RandomUtil.randomString(6);
        authorizationCallBackResponse.setLoginState(tokenState);
        String tokenStateKey = BuildKeyUtils.generateKey(CacheConstant.LOGIN_TOKEN_STATE, tokenState);
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        accessTokenInfo.setAccessToken(oAuth2AccessToken.getValue());
        accessTokenInfo.setTokenType(oAuth2AccessToken.getTokenType());
        accessTokenInfo.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        accessTokenInfo.setExpiration(oAuth2AccessToken.getExpiration());
        String tenant = (String) additionalInformation.get(BaseContextConstants.JWT_KEY_TENANT);
        if (StringUtils.isNotEmpty(tenant)) {
            accessTokenInfo.setTenant(tenant);
        }
        cacheTemplate.set(tokenStateKey, accessTokenInfo, 5L, TimeUnit.MINUTES);
        return authorizationCallBackResponse;
    }

    @Override
    public AccessTokenInfo exchangeToken(String tokenState) {
        String tokenStateKey = BuildKeyUtils.generateKey(CacheConstant.LOGIN_TOKEN_STATE, tokenState);
        AccessTokenInfo accessTokenInfo = cacheTemplate.get(tokenStateKey);
        cacheTemplate.remove(tokenStateKey);
        return accessTokenInfo;
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        try {
            request.logout();
            String token = request.getHeader("token");
            token = StringUtils.removeStartIgnoreCase(token, BaseContextConstants.BEARER_TOKEN);
            if (token != null) {
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
                if (accessToken != null) {
                    tokenStore.removeAccessToken(accessToken);
                    tokenStore.removeRefreshToken(accessToken.getRefreshToken());
                }
            }
            return true;
        } catch (ServletException e) {
            e.printStackTrace();
            return false;
        }
    }
}
