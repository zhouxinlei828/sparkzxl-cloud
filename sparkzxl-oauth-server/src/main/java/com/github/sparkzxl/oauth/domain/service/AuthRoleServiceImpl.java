package com.github.sparkzxl.oauth.domain.service;

import com.github.sparkzxl.oauth.application.service.IAuthRoleService;
import com.github.sparkzxl.oauth.domain.repository.IAuthRoleRepository;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.entity.AuthRole;
import com.github.sparkzxl.oauth.infrastructure.mapper.AuthRoleMapper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
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
    public void deleteAuthRoleRelation(List<Long> ids) {
        authRoleRepository.deleteAuthRoleRelation(ids);
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
