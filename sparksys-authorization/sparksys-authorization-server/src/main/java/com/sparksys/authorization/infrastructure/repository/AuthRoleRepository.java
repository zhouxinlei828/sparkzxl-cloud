package com.sparksys.authorization.infrastructure.repository;


import com.sparksys.authorization.domain.repository.IAuthRoleRepository;
import com.sparksys.authorization.infrastructure.mapper.AuthRoleMapper;
import com.sparksys.authorization.infrastructure.mapper.RoleAuthorityMapper;
import com.sparksys.authorization.infrastructure.mapper.RoleOrgMapper;
import com.sparksys.authorization.infrastructure.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

/**
 * description: 角色 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
@Repository
public class AuthRoleRepository implements IAuthRoleRepository {

    private final AuthRoleMapper authRoleMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleOrgMapper roleOrgMapper;
    private final RoleAuthorityMapper roleAuthorityMapper;

    public AuthRoleRepository(AuthRoleMapper authRoleMapper, UserRoleMapper userRoleMapper, RoleOrgMapper roleOrgMapper, RoleAuthorityMapper roleAuthorityMapper) {
        this.authRoleMapper = authRoleMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleOrgMapper = roleOrgMapper;
        this.roleAuthorityMapper = roleAuthorityMapper;
    }

    @Override
    public boolean deleteAuthRole(Long id) {
        userRoleMapper.deleteByRoleId(id);
        roleOrgMapper.deleteByRoleId(id);
        roleAuthorityMapper.deleteByRoleId(id);
        return authRoleMapper.deleteById(id) == 1;
    }
}
