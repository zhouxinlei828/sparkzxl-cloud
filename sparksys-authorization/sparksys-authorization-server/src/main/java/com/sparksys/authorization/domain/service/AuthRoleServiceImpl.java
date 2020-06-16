package com.sparksys.authorization.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.application.service.IAuthRoleService;
import com.sparksys.authorization.domain.repository.IAuthRoleRepository;
import com.sparksys.authorization.infrastructure.convert.AuthRoleConvert;
import com.sparksys.authorization.infrastructure.po.AuthRoleDO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleUpdateDTO;
import com.sparksys.commons.core.api.result.ApiPageResult;
import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.mybatis.page.PageResult;
import org.springframework.stereotype.Service;

/**
 * description: 角色 服务实现类
 *
 * @Author zhouxinlei
 * @Date 2020-06-07 13:37:09
 */
@Service
public class AuthRoleServiceImpl implements IAuthRoleService {

    private final IAuthRoleRepository authRoleRepository;

    public AuthRoleServiceImpl(IAuthRoleRepository authRoleRepository) {
        this.authRoleRepository = authRoleRepository;
    }

    @Override
    public ApiPageResult listByPage(Integer pageNum, Integer pageSize, String name) {
        Page<AuthRoleDO> roleDOPage = authRoleRepository.listByPage(new Page(pageNum, pageSize), name);
        return PageResult.resetPage(roleDOPage);
    }

    @Override
    public AuthRoleDTO getAuthRole(Long id) {
        AuthRoleDO authRoleDO = authRoleRepository.getAuthRole(id);
        return AuthRoleConvert.INSTANCE.convertAuthUserDTO(authRoleDO);
    }

    @Override
    public boolean saveAuthRole(AuthUser authUser, AuthRoleSaveDTO authRoleSaveDTO) {
        AuthRoleDO authRoleDO = AuthRoleConvert.INSTANCE.convertAuthRoleDO(authRoleSaveDTO);
        authRoleDO.setCreateUser(authUser.getId());
        authRoleDO.setUpdateUser(authUser.getId());
        return authRoleRepository.saveAuthRole(authRoleDO);
    }

    @Override
    public boolean updateAuthRole(AuthUser authUser, AuthRoleUpdateDTO authRoleUpdateDTO) {
        AuthRoleDO authRoleDO = AuthRoleConvert.INSTANCE.convertAuthRoleDO(authRoleUpdateDTO);
        authRoleDO.setUpdateUser(authUser.getId());
        return authRoleRepository.updateAuthRole(authRoleDO);
    }

    @Override
    public boolean deleteAuthRole(Long id) {
        return authRoleRepository.deleteAuthRole(id);
    }

    @Override
    public boolean updateAuthRoleStatus(Long userId, Long roleId, Boolean status) {
        AuthRoleDO authRoleDO = new AuthRoleDO();
        authRoleDO.setId(roleId);
        authRoleDO.setStatus(status);
        authRoleDO.setUpdateUser(userId);
        return authRoleRepository.updateAuthRole(authRoleDO);
    }
}
