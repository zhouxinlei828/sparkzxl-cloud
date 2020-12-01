package com.github.sparkzxl.oauth.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.github.sparkzxl.oauth.domain.model.aggregates.RoleResource;
import com.github.sparkzxl.oauth.domain.repository.IRoleAuthorityRepository;
import com.github.sparkzxl.oauth.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.oauth.infrastructure.mapper.RoleAuthorityMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description: 角色资源绑定 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-08-15 11:14:18
 */
@AllArgsConstructor
@Repository
public class RoleAuthorityRepository implements IRoleAuthorityRepository {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public boolean saveRoleAuthorityBatch(Long roleId, Set<Long> resourceIds, Set<Long> menuIds) {
        List<RoleAuthority> roleAuthorities = Lists.newLinkedList();
        roleAuthorityMapper.delete(new LambdaUpdateWrapper<RoleAuthority>()
                .eq(RoleAuthority::getRoleId, roleId));
        if (CollectionUtils.isNotEmpty(resourceIds)){
            resourceIds.forEach(authorityId -> {
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRoleId(roleId);
                roleAuthority.setAuthorityId(authorityId);
                roleAuthority.setAuthorityType("RESOURCE");
                roleAuthorities.add(roleAuthority);
            });
        }
        if (CollectionUtils.isNotEmpty(menuIds)){
            menuIds.forEach(menuId -> {
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRoleId(roleId);
                roleAuthority.setAuthorityId(menuId);
                roleAuthority.setAuthorityType("MENU");
                roleAuthorities.add(roleAuthority);
            });
        }
        if (CollectionUtils.isNotEmpty(roleAuthorities)){
            roleAuthorityMapper.insertBatchSomeColumn(roleAuthorities);
        }
        return true;
    }

    @Override
    public RoleResource getRoleResource(Long roleId) {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleId(roleId);
        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>()
                .eq(RoleAuthority::getRoleId, roleId));
        List<Long> authMenus = Lists.newArrayList();
        List<Long> authResources = Lists.newArrayList();
        Map<String, List<RoleAuthority>> roleAuthorityMap = roleAuthorities.stream().collect(Collectors.groupingBy(RoleAuthority::getAuthorityType));
        List<RoleAuthority> resourceList = roleAuthorityMap.get("RESOURCE");
        if (CollectionUtils.isNotEmpty(resourceList)){
            authResources = resourceList.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        }
        roleResource.setAuthResources(authResources);
        List<RoleAuthority> menuList = roleAuthorityMap.get("MENU");
        if (CollectionUtils.isNotEmpty(menuList)){
            authMenus = menuList.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        }
        roleResource.setAuthMenus(authMenus);
        return roleResource;
    }
}
