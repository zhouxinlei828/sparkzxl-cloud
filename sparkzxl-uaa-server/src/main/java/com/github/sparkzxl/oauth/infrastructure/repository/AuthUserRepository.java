package com.github.sparkzxl.oauth.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import com.github.sparkzxl.oauth.domain.repository.IAuthUserRepository;
import com.github.sparkzxl.oauth.infrastructure.convert.AuthRoleConvert;
import com.github.sparkzxl.oauth.infrastructure.convert.AuthUserConvert;
import com.github.sparkzxl.oauth.infrastructure.entity.*;
import com.github.sparkzxl.oauth.infrastructure.mapper.*;
import com.github.sparkzxl.database.annonation.InjectionResult;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description：用户仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020/6/5 8:45 下午
 */
@AllArgsConstructor
@Repository
@Slf4j
public class AuthUserRepository implements IAuthUserRepository {

    public final AuthUserMapper authUserMapper;
    private final UserRoleMapper userRoleMapper;
    private final AuthRoleMapper authRoleMapper;
    private final RoleAuthorityMapper roleAuthorityMapper;
    private final AuthResourceMapper authResourceMapper;
    private final AuthMenuMapper authMenuMapper;

    @Override
    @InjectionResult
    public AuthUser selectById(Long id) {
        return authUserMapper.getById(id);
    }

    @Override
    @InjectionResult
    public AuthUser selectByAccount(String account) {
        QueryWrapper<AuthUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AuthUser::getAccount, account);
        queryWrapper.lambda().eq(AuthUser::getStatus, 1);
        return authUserMapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> getAuthUserPermissions(Long id) {
        return authUserMapper.getAuthUserPermissions(id);
    }

    @Override
    public List<String> getAuthUserRoles(Long id) {
        return authUserMapper.getAuthUserRoles(id);
    }

    @Override
    public List<RoleResource> getRoleResourceList() {
        return authUserMapper.getRoleResourceList();
    }

    @Override
    public void deleteUserRelation(List<Long> ids) {
        userRoleMapper.delete(new LambdaUpdateWrapper<UserRole>().in(UserRole::getUserId, ids));
    }

    @Override
    public LoginAuthUser getLoginAuthUser(Long id) {
        String account = BaseContextHandler.getAccount();
        Long userId = BaseContextHandler.getUserId(Long.class);
        String name = BaseContextHandler.getName();
        log.info("当前登录用户信息，account：{}，userId：{}，name：{}", account, userId, name);
        AuthUser authUser = selectById(id);
        LoginAuthUser loginAuthUser = AuthUserConvert.INSTANCE.convertLoginAuthUser(authUser);
        List<Long> roleIds =
                userRoleMapper.selectList(new LambdaUpdateWrapper<UserRole>().eq(UserRole::getUserId, id)).stream().map(UserRole::getRoleId)
                        .collect(Collectors.toList());
        List<RoleAuthority> roleAuthorities =
                roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>().in(RoleAuthority::getRoleId, roleIds)
                        .groupBy(RoleAuthority::getAuthorityId, RoleAuthority::getAuthorityType, RoleAuthority::getRoleId));
        List<Long> authorityIds = roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>().in(RoleAuthority::getRoleId,
                roleIds)).stream().filter(x -> "RESOURCE".equals(x.getAuthorityType()))
                .map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        Map<Long, Long> roleAuthorityIdMap =
                roleAuthorities.stream().collect(Collectors.toMap(RoleAuthority::getAuthorityId, RoleAuthority::getRoleId));
        List<AuthResource> resourceList = authResourceMapper.selectBatchIds(authorityIds);
        List<LoginPermission> loginPermissionList = Lists.newArrayList();
        Map<Long, List<LoginPermission>> loginPermissionMap;
        if (CollectionUtils.isNotEmpty(resourceList)) {
            resourceList.forEach(resource -> {
                LoginPermission loginPermission = new LoginPermission();
                loginPermission.setPermissionId(resource.getCode());
                loginPermission.setPermissionName(resource.getName());
                loginPermission.setRoleId(roleAuthorityIdMap.get(resource.getId()));
                loginPermissionList.add(loginPermission);
            });
        }
        List<Long> menuIds = roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>().in(RoleAuthority::getRoleId,
                roleIds)).stream().filter(x -> "MENU".equals(x.getAuthorityType()))
                .map(RoleAuthority::getAuthorityId).collect(Collectors.toList());

        List<AuthMenu> menuList = authMenuMapper.selectBatchIds(menuIds);
        if (CollectionUtils.isNotEmpty(menuList)) {
            menuList.forEach(menu -> {
                LoginPermission loginPermission = new LoginPermission();
                loginPermission.setPermissionId(menu.getCode());
                loginPermission.setPermissionName(menu.getLabel());
                loginPermission.setRoleId(roleAuthorityIdMap.get(menu.getId()));
                loginPermissionList.add(loginPermission);
            });
        }

        loginPermissionMap = loginPermissionList.stream().collect(Collectors.groupingBy(LoginPermission::getRoleId));
        List<AuthRole> roleList = authRoleMapper.selectBatchIds(roleIds);
        List<LoginRole> loginRoles = AuthRoleConvert.INSTANCE.convertLoginRoles(roleList);
        Map<Long, List<LoginPermission>> finalLoginPermissionMap = loginPermissionMap;
        loginRoles.forEach(x -> x.setPermissions(finalLoginPermissionMap.get(x.getId())));
        loginAuthUser.setRoles(loginRoles);
        return loginAuthUser;
    }


    @Override
    @InjectionResult
    public List<AuthUser> getAuthUserList(AuthUser authUser) {
        LambdaQueryWrapper<AuthUser> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(authUser.getAccount())) {
            queryWrapper.like(AuthUser::getAccount, authUser.getAccount());
        }
        if (StringUtils.isNotEmpty(authUser.getName())) {
            queryWrapper.like(AuthUser::getName, authUser.getName());
        }
        if (ObjectUtils.isNotEmpty(authUser.getStatus())) {
            queryWrapper.eq(AuthUser::getStatus, authUser.getStatus());
        }
        if (ObjectUtils.isNotEmpty(authUser.getSex()) && ObjectUtils.isNotEmpty(authUser.getSex().getCode())) {
            queryWrapper.eq(AuthUser::getSex, authUser.getSex());
        }
        if (ObjectUtils.isNotEmpty(authUser.getNation()) && StringUtils.isNotEmpty(authUser.getNation().getKey())) {
            queryWrapper.eq(AuthUser::getNation, authUser.getNation());
        }
        return authUserMapper.selectList(queryWrapper);
    }
}
