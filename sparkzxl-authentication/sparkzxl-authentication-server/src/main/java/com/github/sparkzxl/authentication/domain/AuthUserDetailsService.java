package com.github.sparkzxl.authentication.domain;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.sparkzxl.authority.application.service.IAuthUserService;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authority.infrastructure.entity.AuthUser;
import com.github.sparkzxl.cache.template.CacheTemplate;
import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.core.entity.CaptchaInfo;
import com.github.sparkzxl.core.spring.SpringContextUtils;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.core.utils.BuildKeyUtils;
import com.github.sparkzxl.jwt.properties.JwtProperties;
import com.github.sparkzxl.jwt.service.JwtTokenService;
import com.github.sparkzxl.security.entity.AuthRequest;
import com.github.sparkzxl.security.entity.AuthToken;
import com.github.sparkzxl.security.entity.AuthUserDetail;
import com.github.sparkzxl.security.entity.LoginStatus;
import com.github.sparkzxl.security.event.LoginEvent;
import com.github.sparkzxl.security.service.AbstractSecurityLoginService;
import com.google.common.collect.Lists;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

import java.util.concurrent.TimeUnit;

import static io.vavr.API.$;
import static io.vavr.API.Case;

/**
 * description: security 加载用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:23:23
 */
@Service
@Slf4j
public class AuthUserDetailsService extends AbstractSecurityLoginService<Long> {

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private CacheTemplate cacheTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private JwtTokenService<Long> jwtTokenService;

    @Autowired
    private UserDetailsService oauthUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthToken login(AuthRequest authRequest) throws AccountNotFoundException, PasswordException {
        String captcha = authRequest.getCaptchaCode();
        if (StringUtils.isNotEmpty(captcha)) {
            String captchaKey = authRequest.getCaptchaKey();
            checkCaptcha(captchaKey, captcha);
        }
        return super.login(authRequest);
    }

    @Override
    public void accessToken(AuthToken authToken, AuthUserDetail<Long> authUserDetail) {
        authUserDetail.setPassword(null);
        log.info("AuthUserInfo json is {}", JSON.toJSONString(authUserDetail));
        AuthUser authUser = authUserService.getById(authUserDetail.getId());
        AuthUserInfo<Long> authUserInfo = new AuthUserInfo<>();
        authUserInfo.setAccount(authUserDetail.getUsername());
        authUserInfo.setId(authUserDetail.getId());
        authUserInfo.setName(authUser.getName());
        authUserInfo.setStatus(authUser.getStatus());
        authUserInfo.setAuthorityList(Lists.newArrayList("admin"));
        String authUserInfoKey = BuildKeyUtils.generateKey(BaseContextConstants.AUTH_USER_TOKEN, authUserInfo.getId());
        Long expiration = authToken.getExpiration();
        redisTemplate.opsForHash().put(authUserInfoKey, authToken.getAccessToken(), authUserInfo);
        redisTemplate.expire(authUserInfoKey, expiration, TimeUnit.SECONDS);
    }

    @Override
    public void checkPasswordError(AuthRequest authRequest, AuthUserDetail<Long> authUserDetail) throws PasswordException {
        //数据库密码比对
        if (!passwordEncoder.matches(authRequest.getPassword(), authUserDetail.getPassword())) {
            SpringContextUtils.publishEvent(new LoginEvent(LoginStatus.pwdError(authUserDetail.getId(),
                    ResponseResultStatus.PASSWORD_ERROR.getMessage())));
            throw new PasswordException("密码不正确");
        }
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
    public JwtProperties getJwtProperties() {
        return this.jwtProperties;
    }

    @Override
    public JwtTokenService<Long> getJwtTokenService() {
        return this.jwtTokenService;
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return this.oauthUserDetailsService;
    }
}
