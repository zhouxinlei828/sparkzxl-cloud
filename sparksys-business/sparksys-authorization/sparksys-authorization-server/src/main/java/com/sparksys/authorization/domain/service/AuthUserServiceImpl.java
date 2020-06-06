package com.sparksys.authorization.domain.service;

import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.infrastructure.convert.AuthUserConvert;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import com.sparksys.authorization.interfaces.dto.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserUpdateDTO;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.security.entity.AdminUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * description: 用户查询 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:57
 */
@Service
@Slf4j
public class AuthUserServiceImpl implements IAuthUserService {

    private final IAuthUserRepository authUserRepository;

    public AuthUserServiceImpl(IAuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AdminUserDetails getAdminUserDetails(String username) {
        AuthUserDO authUserDO = authUserRepository.selectByUserName(username);
        if (ObjectUtils.isNotEmpty(authUserDO)) {
            AdminUserDetails adminUserDetails = new AdminUserDetails();
            adminUserDetails.setAuthUser(AuthUserConvert.INSTANCE.convertAuthUser(authUserDO));
            return adminUserDetails;
        }
        return null;
    }

    @Override
    public boolean saveAuthUser(AuthUser authUser, AuthUserSaveDTO authUserSaveDTO) {
        authUserSaveDTO.setCreateUser(authUser.getId());
        authUserSaveDTO.setUpdateUser(authUser.getId());
        AuthUserDO authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserSaveDTO);
        return authUserRepository.saveAuthUser(authUserDO);
    }

    @Override
    public boolean updateAuthUser(AuthUser authUser, AuthUserUpdateDTO authUserUpdateDTO) {
        authUserUpdateDTO.setUpdateUser(authUser.getId());
        AuthUserDO authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserUpdateDTO);
        return authUserRepository.updateAuthUser(authUserDO);
    }

    @Override
    public boolean deleteAuthUser(Long id) {
        return authUserRepository.deleteAuthUser(id);
    }

    @Override
    public boolean updateAuthUserStatus(AuthUser authUser, AuthUserStatusDTO authUserStatusDTO) {
        authUserStatusDTO.setUpdateUser(authUser.getId());
        AuthUserDO authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserStatusDTO);
        return authUserRepository.updateAuthUser(authUserDO);
    }
}
