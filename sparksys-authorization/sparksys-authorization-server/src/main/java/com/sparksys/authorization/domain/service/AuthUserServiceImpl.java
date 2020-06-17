package com.sparksys.authorization.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.domain.constant.AuthorizationConstant;
import com.sparksys.authorization.infrastructure.convert.AuthUserConvert;
import com.sparksys.authorization.infrastructure.entity.AuthUser;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import com.sparksys.authorization.interfaces.dto.user.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserUpdateDTO;
import com.sparksys.commons.core.api.result.ApiPageResult;
import com.sparksys.commons.mybatis.page.PageResult;
import com.sparksys.commons.security.entity.AdminUserDetails;
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
public class AuthUserServiceImpl implements IAuthUserService {

    private final IAuthUserRepository authUserRepository;

    public AuthUserServiceImpl(IAuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AdminUserDetails getAdminUserDetails(String account) {
        AuthUser authUser = authUserRepository.selectByAccount(account);
        if (ObjectUtils.isNotEmpty(authUser)) {
            AdminUserDetails adminUserDetails = new AdminUserDetails();
            adminUserDetails.setAuthUser(AuthUserConvert.INSTANCE.convertAuthUser(authUser));
            return adminUserDetails;
        }
        return null;
    }

    @Override
    public boolean saveAuthUser(com.sparksys.commons.core.entity.AuthUser authUser, AuthUserSaveDTO authUserSaveDTO) {
        authUserSaveDTO.setCreateUser(authUser.getId());
        authUserSaveDTO.setUpdateUser(authUser.getId());
        AuthUser authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserSaveDTO);
        return authUserRepository.saveAuthUser(authUserDO);
    }

    @Override
    public boolean updateAuthUser(com.sparksys.commons.core.entity.AuthUser authUser, AuthUserUpdateDTO authUserUpdateDTO) {
        authUserUpdateDTO.setUpdateUser(authUser.getId());
        AuthUser authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserUpdateDTO);
        return authUserRepository.updateAuthUser(authUserDO);
    }

    @Override
    public boolean deleteAuthUser(Long id) {
        return authUserRepository.deleteAuthUser(id);
    }

    @Override
    public boolean updateAuthUserStatus(com.sparksys.commons.core.entity.AuthUser authUser, AuthUserStatusDTO authUserStatusDTO) {
        authUserStatusDTO.setUpdateUser(authUser.getId());
        AuthUser authUserDO = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserStatusDTO);
        return authUserRepository.updateAuthUser(authUserDO);
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
        AuthUser authUser = authUserRepository.selectById(id);
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
        return authUserRepository.updateAuthUser(authUser);
    }

    @Override
    public boolean incrPasswordErrorNumById(Long id) {
        return authUserRepository.incrPasswordErrorNumById(id);
    }
}
