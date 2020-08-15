package com.sparksys.authority.infrastructure.repository;

import com.google.common.collect.Lists;
import com.sparksys.authority.domain.repository.IRoleAuthorityRepository;
import com.sparksys.authority.infrastructure.entity.RoleAuthority;
import com.sparksys.authority.infrastructure.mapper.RoleAuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * description: 角色资源绑定 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-08-15 11:14:18
 */
@Repository
public class RoleAuthorityRepository implements IRoleAuthorityRepository {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public boolean saveRoleAuthorityBatch(Long roleId, String authorityType, Set<Long> authorityList) {
        List<RoleAuthority> roleAuthorities = Lists.newLinkedList();
        authorityList.forEach(authorityId -> {
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoleId(roleId);
            roleAuthority.setAuthorityId(authorityId);
            roleAuthority.setAuthorityType(authorityType);
            roleAuthorities.add(roleAuthority);
        });
        return roleAuthorityMapper.insertBatchSomeColumn(roleAuthorities) > 0;
    }
}
