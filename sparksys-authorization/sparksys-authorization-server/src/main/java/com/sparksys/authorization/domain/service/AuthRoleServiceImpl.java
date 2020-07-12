package com.sparksys.authorization.domain.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.commons.core.constant.CacheKey;
import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.commons.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.IAuthRoleService;
import com.sparksys.authorization.domain.repository.IAuthRoleRepository;
import com.sparksys.authorization.infrastructure.convert.AuthRoleConvert;
import com.sparksys.authorization.infrastructure.entity.AuthRole;
import com.sparksys.authorization.infrastructure.mapper.AuthRoleMapper;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleUpdateDTO;
import com.sparksys.commons.database.utils.PageInfoUtils;
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
        PageHelper.startPage(pageNum,pageSize);
        return PageInfoUtils.pageInfo(authRoleRepository.listByName(name));
    }

    @Override
    public AuthRoleDTO getAuthRole(Long id) {
        AuthRole authRole = getByIdCache(id);
        return AuthRoleConvert.INSTANCE.convertAuthUserDTO(authRole);
    }

    @Override
    public boolean saveAuthRole(GlobalAuthUser authUser, AuthRoleSaveDTO authRoleSaveDTO) {
        AuthRole authRole = AuthRoleConvert.INSTANCE.convertAuthRoleDO(authRoleSaveDTO);
        authRole.setCreateUser(authUser.getId());
        authRole.setUpdateUser(authUser.getId());
        return save(authRole);
    }

    @Override
    public boolean updateAuthRole(GlobalAuthUser authUser, AuthRoleUpdateDTO authRoleUpdateDTO) {
        AuthRole authRole = AuthRoleConvert.INSTANCE.convertAuthRoleDO(authRoleUpdateDTO);
        authRole.setUpdateUser(authUser.getId());
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
