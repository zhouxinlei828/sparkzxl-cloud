package com.sparksys.commons.security.service;

import com.sparksys.commons.core.constant.AuthConstant;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.core.support.ResponseResultStatus;
import com.sparksys.commons.web.component.SpringContextUtils;
import com.sparksys.commons.core.constant.CoreConstant;
import com.sparksys.commons.core.support.BusinessException;
import com.sparksys.commons.core.utils.crypto.MD5Utils;
import com.sparksys.commons.redis.cache.CacheProviderService;
import com.sparksys.commons.redis.cache.RedisCacheProviderImpl;
import com.sparksys.commons.security.entity.AdminUserDetails;
import com.sparksys.commons.security.entity.AuthToken;
import com.sparksys.commons.security.request.AuthRequest;
import com.sparksys.commons.core.utils.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

/**
 * description: 登录授权Service
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:39:06
 */
@Slf4j
public abstract class AbstractAuthDetailsService {

    /**
     * 登录
     *
     * @param authRequest 登录认证
     * @return java.lang.String
     * @throws Exception 异常
     */
    public AuthToken login(AuthRequest authRequest) throws Exception {
        String account = authRequest.getAccount();
        ResponseResultStatus.USERNAME_EMPTY.assertNotNull(account);

        AdminUserDetails adminUserDetails = getAdminUserDetail(account);
        String password = authRequest.getPassword();
        ResponseResultStatus.PASSWORD_EMPTY.assertNotNull(password);

        String token;
        ResponseResultStatus.ACCOUNT_EMPTY.assertNotNull(adminUserDetails);

        AuthUser authUser = adminUserDetails.getAuthUser();
        //加密数据
        String encryptPassword = MD5Utils.encrypt(authRequest.getPassword());
        log.info("密码加密 = {}，数据库密码={}", password, encryptPassword);
        //数据库密码比对
        if (!StringUtils.equals(encryptPassword, authUser.getPassword())) {
            ResponseResultStatus.PASSWORD_ERROR.newException(password);
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminUserDetails,
                null, adminUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = JwtTokenUtil.generateToken(adminUserDetails.getUsername());
        authUser.setPassword(null);
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setExpiration(CoreConstant.JwtTokenConstant.JWT_EXPIRATION);
        authToken.setAuthUser(authUser);
        //设置accessToken缓存
        accessToken(authToken, authUser);
        return authToken;
    }

    /**
     * 设置accessToken缓存
     *
     * @param authToken 用户token
     * @param authUser  认证用户
     * @return void
     */
    public void accessToken(AuthToken authToken, AuthUser authUser) {
        CacheProviderService cacheProviderService = SpringContextUtils.getBean(RedisCacheProviderImpl.class);
        String token = authToken.getToken();
        cacheProviderService.set(AuthConstant.AUTH_USER + token, authUser,
                authToken.getExpiration());
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param account 用户名
     * @return AuthUser
     * @throws AccountNotFoundException 异常
     */
    public abstract AuthUser getOauthUserInfo(String account) throws AccountNotFoundException;

    /**
     * 根据用户名获取用户信息
     *
     * @param account 用户名
     * @return AdminUserDetails
     * @throws BusinessException 异常
     */
    public AdminUserDetails getAdminUserDetail(String account) throws AccountNotFoundException {
        AdminUserDetails adminUserDetails = new AdminUserDetails();
        AuthUser authUser = getOauthUserInfo(account);
        if (authUser != null) {
            adminUserDetails.setAuthUser(authUser);
            return adminUserDetails;
        }
        throw new AccountNotFoundException("账户不存在");
    }

    /**
     * 获取用户权限
     *
     * @param adminId 用户id
     * @return List<String>
     * @author zhouxinlei
     * @date 2019-09-11 17:46:56
     */
    public abstract List<String> listOauthPermission(Long adminId);

}
