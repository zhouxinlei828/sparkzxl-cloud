package com.github.sparkzxl.authority.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.sparkzxl.authority.domain.repository.IAuthRoleRepository;
import com.github.sparkzxl.authority.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.authority.infrastructure.entity.RoleOrg;
import com.github.sparkzxl.authority.infrastructure.entity.UserRole;
import com.github.sparkzxl.authority.infrastructure.mapper.AuthRoleMapper;
import com.github.sparkzxl.authority.infrastructure.mapper.RoleAuthorityMapper;
import com.github.sparkzxl.authority.infrastructure.mapper.RoleOrgMapper;
import com.github.sparkzxl.authority.infrastructure.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void deleteAuthRoleRelation(List<Long> ids) {
        userRoleMapper.delete(new LambdaUpdateWrapper<UserRole>().in(UserRole::getRoleId, ids));
        roleOrgMapper.delete(new LambdaUpdateWrapper<RoleOrg>().eq(RoleOrg::getRoleId, ids));
        roleAuthorityMapper.delete(new LambdaUpdateWrapper<RoleAuthority>().eq(RoleAuthority::getRoleId, ids));
    }
}
