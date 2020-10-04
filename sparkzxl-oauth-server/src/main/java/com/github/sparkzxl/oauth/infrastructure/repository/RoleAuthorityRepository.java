package com.github.sparkzxl.oauth.infrastructure.repository;

import com.github.sparkzxl.oauth.domain.repository.IRoleAuthorityRepository;
import com.github.sparkzxl.oauth.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.oauth.infrastructure.mapper.RoleAuthorityMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * description: 角色资源绑定 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-08-15 11:14:18
 */
@AllArgsConstructor
@Repository
public class RoleAuthorityRepository implements IRoleAuthorityRepository {

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
