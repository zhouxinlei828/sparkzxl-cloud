package com.sparksys.oauth.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Sets;
import com.sparksys.commons.core.constant.CacheKey;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.core.utils.collection.ListUtils;
import com.sparksys.commons.mybatis.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.commons.security.entity.AuthUserDetail;
import com.sparksys.oauth.application.service.IAuthUserService;
import com.sparksys.oauth.domain.constant.AuthorizationConstant;
import com.sparksys.oauth.domain.repository.IAuthUserRepository;
import com.sparksys.oauth.infrastructure.convert.AuthUserConvert;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.mapper.AuthUserMapper;
import com.sparksys.oauth.interfaces.dto.user.AuthUserDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserUpdateDTO;
import com.sparksys.commons.core.base.api.result.ApiPageResult;
import com.sparksys.commons.mybatis.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public boolean saveAuthUser(Long contextUserId, AuthUserSaveDTO authUserSaveDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserSaveDTO);
        authUser.setStatus(true);
        String password = passwordEncoder.encode(authUserSaveDTO.getPassword());
        authUser.setPassword(password);
        authUser.setCreateUser(contextUserId);
        authUser.setUpdateUser(contextUserId);
        return save(authUser);
    }

    @Override
    public boolean updateAuthUser(Long contextUserId, AuthUserUpdateDTO authUserUpdateDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserUpdateDTO);
        authUser.setUpdateUser(contextUserId);
        return updateAllById(authUser);
    }

    @Override
    public boolean deleteAuthUser(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateAuthUserStatus(Long contextUserId, AuthUserStatusDTO authUserStatusDTO) {
        authUserStatusDTO.setUpdateUser(contextUserId);
        AuthUser authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserStatusDTO);
        return updateById(authUserDO);
    }

    @Override
    public ApiPageResult listByPage(Integer pageNum, Integer pageSize, String name) {
        Page<AuthUser> userDOIPage = authUserRepository.listByPage(new Page(pageNum, pageSize), name);
        List<AuthUser> authUserList = userDOIPage.getRecords();
        List<AuthUserDTO> authUserDTOS =
                authUserList.stream().map(authUserDO -> {
                    AuthUserDTO authUserDTO = AuthUserConvert.INSTANCE.convertAuthUserDTO(authUserDO);
                    String sex = AuthorizationConstant.SEX_MAP.get(authUserDO.getSex());
                    authUserDTO.setSex(sex);
                    return authUserDTO;
                }).collect(Collectors.toList());
        return PageResult.resetPage(userDOIPage, authUserDTOS);
    }

    @Override
    public AuthUserDTO getAuthUser(Long id) {
        AuthUser authUser = getByIdCache(id);
        AuthUserDTO authUserDTO = AuthUserConvert.INSTANCE.convertAuthUserDTO(authUser);
        String sex = AuthorizationConstant.SEX_MAP.get(authUser.getSex());
        authUserDTO.setSex(sex);
        return authUserDTO;
    }

    @Override
    public boolean resetPassErrorNum(Long id) {
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        return updateById(authUser);
    }

    @Override
    public boolean resetPassErrorNum(String account) {
        AuthUser authUser = new AuthUser();
        authUser.setAccount(account);
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        return updateById(authUser);
    }

    @Override
    public boolean incrPasswordErrorNum(Long id) {
        return authUserRepository.incrPasswordErrorNumById(id);
    }

    @Override
    public boolean incrPasswordErrorNum(String account) {
        return authUserRepository.incrPasswordErrorNumByAccount(account);
    }

    @Override
    public AuthUserDetail getAuthUserDetail(String username) {
        AuthUser authUser = authUserRepository.selectByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            GlobalAuthUser globalAuthUser = AuthUserConvert.INSTANCE.convertGlobalAuthUser(authUser);
            List<String> userPermissions = authUserRepository.getAuthUserPermissions(authUser.getId());
            globalAuthUser.setPermissions(userPermissions);
            return new AuthUserDetail(globalAuthUser);
        }
        return null;
    }

    @Override
    public Set<String> getAuthUserPermissions(Long id) {
        List<String> userPermissions = authUserRepository.getAuthUserPermissions(id);
        if (ListUtils.isNotEmpty(userPermissions)) {
            return new HashSet<>(userPermissions);
        }
        return Sets.newHashSet();
    }

    @Override
    protected String getRegion() {
        return CacheKey.USER;
    }
}
