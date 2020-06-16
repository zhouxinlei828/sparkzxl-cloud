package com.sparksys.authorization.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.domain.repository.IAuthRoleRepository;
import com.sparksys.authorization.infrastructure.mapper.AuthRoleMapper;
import com.sparksys.authorization.infrastructure.po.AuthRoleDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * description: 角色 仓储层实现类
 *
 * @Author zhouxinlei
 * @Date 2020-06-07 13:31:48
 */
@Repository
public class AuthRoleRepository implements IAuthRoleRepository {

    private final AuthRoleMapper authRoleMapper;

    public AuthRoleRepository(AuthRoleMapper authRoleMapper) {
        this.authRoleMapper = authRoleMapper;
    }


    @Override
    public Page<AuthRoleDO> listByPage(Page page, String name) {
        QueryWrapper<AuthRoleDO> roleDOQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equalsIgnoreCase(name)) {
            roleDOQueryWrapper.like("name", name);
        }
        return authRoleMapper.selectPage(page, roleDOQueryWrapper);
    }

    @Override
    public AuthRoleDO getAuthRole(Long id) {
        return authRoleMapper.selectById(id);
    }

    @Override
    public boolean saveAuthRole(AuthRoleDO authRoleDO) {
        return authRoleMapper.insert(authRoleDO) == 1;
    }

    @Override
    public boolean updateAuthRole(AuthRoleDO authRoleDO) {
        return authRoleMapper.updateById(authRoleDO) == 1;
    }

    @Override
    public boolean deleteAuthRole(Long id) {
        authRoleMapper.deleteUserRoleRelation(id);
        authRoleMapper.deleteRoleAuthorityRelation(id);
        authRoleMapper.deleteRoleOrgRelation(id);
        return authRoleMapper.deleteById(id) == 1;
    }
}
