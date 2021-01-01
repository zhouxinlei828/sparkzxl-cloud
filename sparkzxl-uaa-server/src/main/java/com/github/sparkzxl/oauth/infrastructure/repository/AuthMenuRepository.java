package com.github.sparkzxl.oauth.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.sparkzxl.oauth.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.oauth.domain.repository.IAuthMenuRepository;
import com.github.sparkzxl.oauth.infrastructure.entity.AuthMenu;
import com.github.sparkzxl.oauth.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.oauth.infrastructure.entity.UserRole;
import com.github.sparkzxl.oauth.infrastructure.mapper.AuthMenuMapper;
import com.github.sparkzxl.oauth.infrastructure.mapper.RoleAuthorityMapper;
import com.github.sparkzxl.oauth.infrastructure.mapper.UserRoleMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 菜单 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:12
 */
@Repository
public class AuthMenuRepository implements IAuthMenuRepository {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AuthMenuMapper authMenuMapper;


    @Override
    public List<MenuBasicInfo> getAuthMenuList(Long userId) {
        List<Long> roleIds =
                userRoleMapper.selectList(new LambdaUpdateWrapper<UserRole>().eq(UserRole::getUserId, userId)).stream().map(UserRole::getRoleId)
                        .collect(Collectors.toList());
        List<RoleAuthority> roleAuthorities =
                roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>().in(RoleAuthority::getRoleId, roleIds)
                        .groupBy(RoleAuthority::getAuthorityId, RoleAuthority::getAuthorityType, RoleAuthority::getRoleId));
        List<Long> menuIds = roleAuthorities.stream().filter(x -> "MENU".equals(x.getAuthorityType()))
                .map(RoleAuthority::getAuthorityId).collect(Collectors.toList());

        List<AuthMenu> menuList = authMenuMapper.selectBatchIds(menuIds);
        menuList = menuList.stream().sorted(Comparator.comparing(AuthMenu::getSortValue)).collect(Collectors.toList());
        List<MenuBasicInfo> menuBasicInfos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(menuList)) {
            menuList.forEach(menu -> {
                MenuBasicInfo menuBasicInfo = new MenuBasicInfo();
                menuBasicInfo.setId(menu.getId());
                menuBasicInfo.setLabel(menu.getLabel());
                menuBasicInfo.setIcon(menu.getIcon());
                menuBasicInfo.setPath(menu.getPath());
                menuBasicInfo.setComponent(menu.getComponent());
                menuBasicInfo.setParentId(menu.getParentId());
                menuBasicInfo.setSortValue(menu.getSortValue());
                menuBasicInfos.add(menuBasicInfo);
            });
            return menuBasicInfos;
        }
        return Lists.newArrayList();
    }
}
