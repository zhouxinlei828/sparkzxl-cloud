package com.sparksys.oauth.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.oauth.domain.repository.IAuthRoleRepository;
import com.sparksys.oauth.infrastructure.entity.AuthRole;
import com.sparksys.oauth.infrastructure.mapper.AuthRoleMapper;
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

    public AuthRoleRepository(AuthRoleMapper authRoleMapper) {
        this.authRoleMapper = authRoleMapper;
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
        authRoleMapper.deleteUserRoleRelation(id);
        authRoleMapper.deleteRoleAuthorityRelation(id);
        authRoleMapper.deleteRoleOrgRelation(id);
        return authRoleMapper.deleteById(id) == 1;
    }
}
