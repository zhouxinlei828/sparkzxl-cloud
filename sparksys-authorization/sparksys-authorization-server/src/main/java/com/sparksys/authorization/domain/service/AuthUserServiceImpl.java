package com.sparksys.authorization.domain.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.authorization.infrastructure.constant.CacheConstant;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.core.utils.Md5Utils;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.database.utils.PageInfoUtils;
import com.sparksys.security.entity.AuthUserDetail;
import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import com.sparksys.authorization.infrastructure.convert.AuthUserConvert;
import com.sparksys.authorization.infrastructure.entity.AuthUser;
import com.sparksys.authorization.infrastructure.mapper.AuthUserMapper;
import com.sparksys.authorization.interfaces.dto.user.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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

    private final IAuthUserRepository authUserRepository;

    public AuthUserServiceImpl(IAuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    @Override
    public boolean saveAuthUser(Long contextUserId, AuthUserSaveDTO authUserSaveDTO) {
        AuthUser authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserSaveDTO);
        authUserDO.setStatus(true);
        String password = Md5Utils.encrypt(authUserDO.getPassword());
        authUserDO.setPassword(password);
        authUserDO.setCreateUser(contextUserId);
        authUserDO.setUpdateUser(contextUserId);
        return save(authUserDO);
    }

    @Override
    public boolean updateAuthUser(Long contextUserId, AuthUserUpdateDTO authUserUpdateDTO) {
        AuthUser authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserUpdateDTO);
        authUserDO.setUpdateUser(contextUserId);
        return updateById(authUserDO);
    }

    @Override
    public boolean deleteAuthUser(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateAuthUserStatus(Long contextUserId, AuthUserStatusDTO authUserStatusDTO) {
        AuthUser authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserStatusDTO);
        authUserDO.setUpdateUser(contextUserId);
        return updateById(authUserDO);
    }

    @Override
    public PageInfo<AuthUserDTO> listByPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthUser> authUserList = authUserRepository.listByName(name);
        List<AuthUserDTO> authUsers =
                authUserList.stream().map(AuthUserConvert.INSTANCE::convertAuthUserDTO).collect(Collectors.toList());
        return PageInfoUtils.pageInfo(authUsers);
    }

    @Override
    public AuthUserDTO getAuthUser(Long id) {
        AuthUser authUser = getByIdCache(id);
        return AuthUserConvert.INSTANCE.convertAuthUserDTO(authUser);
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
            AuthUserInfo authUserInfo = AuthUserConvert.INSTANCE.convertAuthUserInfo(authUser);
            List<String> userPermissions = authUserRepository.getAuthUserPermissions(authUser.getId());
            authUserInfo.setAuthorityList(userPermissions);
            return new AuthUserDetail(authUserInfo);
        }
        return null;
    }

    @Override
    protected String getRegion() {
        return CacheConstant.USER;
    }
}
