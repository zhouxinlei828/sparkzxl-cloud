package com.sparksys.authorization.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.application.service.IAuthUserService;
import com.sparksys.authorization.domain.constant.AuthorizationConstant;
import com.sparksys.authorization.infrastructure.convert.AuthUserConvert;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import com.sparksys.authorization.interfaces.dto.user.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.authorization.interfaces.dto.user.AuthUserUpdateDTO;
import com.sparksys.commons.core.api.result.ApiPageResult;
import com.sparksys.commons.core.entity.AuthUser;
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

    @Override
    public ApiPageResult listByPage(Integer pageNum, Integer pageSize, String name) {
        Page<AuthUserDO> userDOIPage = authUserRepository.listByPage(new Page(pageNum, pageSize), name);
        List<AuthUserDO> authUserDOList = userDOIPage.getRecords();
        List<AuthUserDTO> authUserDTOS =
                authUserDOList.stream().map(authUserDO -> {
                    AuthUserDTO authUserDTO = AuthUserConvert.INSTANCE.convertAuthUserDTO(authUserDO);
                    String sex = AuthorizationConstant.SEX_MAP.get(authUserDO.getSex());
                    authUserDTO.setSex(sex);
                    return authUserDTO;
                }).collect(Collectors.toList());
        return PageResult.resetPage(userDOIPage, authUserDTOS);
    }

    @Override
    public AuthUserDTO getAuthUser(Long id) {
        AuthUserDO authUserDO = authUserRepository.selectById(id);
        AuthUserDTO authUserDTO = AuthUserConvert.INSTANCE.convertAuthUserDTO(authUserDO);
        String sex = AuthorizationConstant.SEX_MAP.get(authUserDO.getSex());
        authUserDTO.setSex(sex);
        return authUserDTO;
    }
}
