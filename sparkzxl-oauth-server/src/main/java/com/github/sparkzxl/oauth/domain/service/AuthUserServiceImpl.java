package com.github.sparkzxl.oauth.domain.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.oauth.server.entity.AuthUserDetail;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.entity.*;
import com.github.sparkzxl.oauth.application.service.IAuthUserService;
import com.github.sparkzxl.oauth.domain.repository.IAuthUserRepository;
import com.github.sparkzxl.oauth.infrastructure.convert.AuthUserConvert;
import com.github.sparkzxl.oauth.infrastructure.mapper.AuthUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * description: 用户查询 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:57
 */
@Service
@Slf4j
public class AuthUserServiceImpl extends AbstractSuperCacheServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

    @Autowired
    private IAuthUserRepository authUserRepository;

    @Override
    public void resetPassErrorNum(Long id) {
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        updateById(authUser);
    }

    @Override
    public void resetPassErrorNum(String account) {
        AuthUser authUser = new AuthUser();
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        UpdateWrapper<AuthUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("account", account);
        update(authUser, userUpdateWrapper);
    }

    @Override
    public void incrPasswordErrorNum(Long id) {
        authUserRepository.incrPasswordErrorNumById(id);
    }

    @Override
    public void incrPasswordErrorNum(String account) {
        authUserRepository.incrPasswordErrorNumByAccount(account);
    }

    @Override
    public AuthUserDetail getAuthUserDetail(String username) {
        AuthUser authUser = authUserRepository.selectByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            AuthUserInfo authUserInfo = AuthUserConvert.INSTANCE.convertAuthUserInfo(authUser);
            List<String> userRoles = authUserRepository.getAuthUserRoles(authUser.getId());
            authUserInfo.setAuthorityList(userRoles);
            return new AuthUserDetail(authUserInfo);
        }
        return null;
    }

    @Override
    protected String getRegion() {
        return CacheConstant.USER;
    }

}
