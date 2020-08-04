package com.sparksys.authorization.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.IAuthRoleService;
import com.sparksys.authorization.domain.repository.IAuthRoleRepository;
import com.sparksys.authorization.infrastructure.constant.CacheConstant;
import com.sparksys.authorization.infrastructure.entity.AuthRole;
import com.sparksys.authorization.infrastructure.mapper.AuthRoleMapper;
import org.springframework.stereotype.Service;

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
    public boolean deleteAuthRole(Long id) {
        return authRoleRepository.deleteAuthRole(id);
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
        return CacheConstant.ROLE;
    }
}
