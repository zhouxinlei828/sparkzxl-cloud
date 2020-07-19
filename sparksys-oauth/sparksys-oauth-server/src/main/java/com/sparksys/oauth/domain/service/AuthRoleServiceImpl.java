package com.sparksys.oauth.domain.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.core.constant.CacheKey;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.database.utils.PageInfoUtils;
import com.sparksys.oauth.application.service.IAuthRoleService;
import com.sparksys.oauth.domain.repository.IAuthRoleRepository;
import com.sparksys.oauth.infrastructure.convert.AuthRoleConvert;
import com.sparksys.oauth.infrastructure.entity.AuthRole;
import com.sparksys.oauth.infrastructure.mapper.AuthRoleMapper;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 角色 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:37:09
 */
@Service
public class AuthRoleServiceImpl extends AbstractSuperCacheServiceImpl<AuthRoleMapper, AuthRole> implements IAuthRoleService {

    private final IAuthRoleRepository authRoleRepository;

    public AuthRoleServiceImpl(IAuthRoleRepository authRoleRepository) {
        this.authRoleRepository = authRoleRepository;
    }

    @Override
    public PageInfo<AuthRole> listByPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthRole> authRoles = authRoleRepository.listByName(name);
        return PageInfoUtils.pageInfo(authRoles);
    }

    @Override
    public AuthRoleDTO getAuthRole(Long id) {
        AuthRole authRole = getByIdCache(id);
        return AuthRoleConvert.INSTANCE.convertAuthUserDTO(authRole);
    }

    @Override
    public boolean saveAuthRole(Long userId, AuthRoleSaveDTO authRoleSaveDTO) {
        AuthRole authRole = AuthRoleConvert.INSTANCE.convertAuthRoleDO(authRoleSaveDTO);
        authRole.setCreateUser(userId);
        authRole.setUpdateUser(userId);
        return save(authRole);
    }

    @Override
    public boolean updateAuthRole(Long userId, AuthRoleUpdateDTO authRoleUpdateDTO) {
        AuthRole authRole = AuthRoleConvert.INSTANCE.convertAuthRoleDO(authRoleUpdateDTO);
        authRole.setUpdateUser(userId);
        return updateById(authRole);
    }

    @Override
    public boolean deleteAuthRole(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateAuthRoleStatus(Long userId, Long roleId, Boolean status) {
        AuthRole authRole = new AuthRole();
        authRole.setId(roleId);
        authRole.setStatus(status);
        authRole.setUpdateUser(userId);
        return updateById(authRole);
    }

    @Override
    protected String getRegion() {
        return CacheKey.ROLE;
    }
}
