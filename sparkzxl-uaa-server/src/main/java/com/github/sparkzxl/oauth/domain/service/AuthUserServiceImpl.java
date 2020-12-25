package com.github.sparkzxl.oauth.domain.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import com.github.sparkzxl.oauth.application.service.IAuthUserService;
import com.github.sparkzxl.oauth.domain.repository.IAuthUserRepository;
import com.github.sparkzxl.oauth.entity.AuthUserDetail;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.convert.AuthUserConvert;
import com.github.sparkzxl.oauth.infrastructure.entity.*;
import com.github.sparkzxl.oauth.infrastructure.mapper.AuthUserMapper;
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserPageDTO;
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.user.AuthUserUpdateDTO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthUserDTO getAuthUser(Long id) {
        return AuthUserConvert.INSTANCE.convertAuthUserDTO(getByIdCache(id));
    }


    @Override
    protected String getRegion() {
        return CacheConstant.USER;
    }


    @Override
    public void deleteOrgId(Long id) {
        UpdateWrapper<AuthUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("org_id", null);
        userUpdateWrapper.eq("org_id", id);
        update(userUpdateWrapper);
    }

    @Override
    public void deleteUserRelation(List<Long> ids) {
        authUserRepository.deleteUserRelation(ids);
    }

    @Override
    public LoginAuthUser getLoginAuthUser(Long id) {
        return authUserRepository.getLoginAuthUser(id);
    }

    @Override
    public AuthUserDetail<Long> getAuthUserDetail(String username) {
        AuthUser authUser = authUserRepository.selectByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            AuthUserInfo<Long> authUserInfo = getAuthUserInfo(username);
            return new AuthUserDetail<>(authUser.getId(),
                    authUser.getAccount(),
                    authUser.getPassword(),
                    authUser.getName(),
                    authUser.getStatus(),
                    authUserInfo.getAuthorityList());
        }
        return null;
    }

    @Override
    public AuthUserInfo<Long> getAuthUserInfo(String username) {
        AuthUser authUser = authUserRepository.selectByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            AuthUserInfo<Long> authUserInfo = AuthUserConvert.INSTANCE.convertAuthUserInfo(authUser);
            List<String> userRoles = authUserRepository.getAuthUserRoles(authUser.getId());
            authUserInfo.setAuthorityList(userRoles);
            Map<String, Object> extraInfo = Maps.newHashMap();
            extraInfo.put("org",authUser.getOrg().getData());
            extraInfo.put("station",authUser.getStation());
            extraInfo.put("mobile",authUser.getMobile());
            extraInfo.put("email",authUser.getEmail());
            extraInfo.put("education",authUser.getEducation());
            extraInfo.put("positionStatus",authUser.getPositionStatus());
            authUserInfo.setExtraInfo(extraInfo);
            return authUserInfo;
        }
        return null;
    }

    @Override
    public PageInfo<AuthUser> getAuthUserPage(AuthUserPageDTO authUserPageDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUser(authUserPageDTO);
        PageHelper.startPage(authUserPageDTO.getPageNum(), authUserPageDTO.getPageSize());
        List<AuthUser> authUserList = authUserRepository.getAuthUserList(authUser);
        return PageInfoUtils.pageInfo(authUserList);
    }

    @Override
    public boolean saveAuthUser(AuthUserSaveDTO authUserSaveDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUser(authUserSaveDTO);
        String password = passwordEncoder.encode(authUser.getPassword());
        authUser.setPassword(password);
        return save(authUser);
    }

    @Override
    public boolean updateAuthUser(AuthUserUpdateDTO authUserUpdateDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUser(authUserUpdateDTO);
        return updateById(authUser);
    }

    @Override
    public void deleteOrgIds(List<Long> orgIds) {
        UpdateWrapper<AuthUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("org_id", null);
        userUpdateWrapper.in("org_id", orgIds);
        update(userUpdateWrapper);
    }
}
