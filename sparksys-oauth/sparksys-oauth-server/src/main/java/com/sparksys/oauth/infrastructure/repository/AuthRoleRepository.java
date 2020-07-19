package com.sparksys.oauth.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.oauth.domain.repository.IAuthRoleRepository;
import com.sparksys.oauth.infrastructure.entity.AuthRole;
import com.sparksys.oauth.infrastructure.mapper.AuthRoleMapper;
import com.sparksys.oauth.infrastructure.mapper.RoleAuthorityMapper;
import com.sparksys.oauth.infrastructure.mapper.RoleOrgMapper;
import com.sparksys.oauth.infrastructure.mapper.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
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
    public List<AuthRole> listByName(String name) {
        QueryWrapper<AuthRole> authRoleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equalsIgnoreCase(name)) {
            authRoleQueryWrapper.likeRight("name", name);
        }
        return authRoleMapper.selectList(authRoleQueryWrapper);
    }

    @Override
    public AuthRole getAuthRole(Long id) {
        return authRoleMapper.selectById(id);
    }

    @Override
    public boolean saveAuthRole(AuthRole authRole) {
        return authRoleMapper.insert(authRole) == 1;
    }

    @Override
    public boolean updateAuthRole(AuthRole authRole) {
        return authRoleMapper.updateById(authRole) == 1;
    }

    @Override
    public boolean deleteAuthRole(Long id) {
        userRoleMapper.deleteByRoleId(id);
        roleOrgMapper.deleteByRoleId(id);
        roleAuthorityMapper.deleteByRoleId(id);
        return authRoleMapper.deleteById(id) == 1;
    }
}
