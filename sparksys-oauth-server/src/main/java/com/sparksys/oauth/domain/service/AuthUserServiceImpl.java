package com.sparksys.oauth.domain.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.entity.AuthUserDetail;
import com.sparksys.oauth.infrastructure.constant.CacheConstant;
import com.sparksys.oauth.infrastructure.entity.*;
import com.sparksys.oauth.application.service.IAuthUserService;
import com.sparksys.oauth.domain.repository.IAuthUserRepository;
import com.sparksys.oauth.infrastructure.convert.AuthUserConvert;
import com.sparksys.oauth.infrastructure.mapper.AuthUserMapper;
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
