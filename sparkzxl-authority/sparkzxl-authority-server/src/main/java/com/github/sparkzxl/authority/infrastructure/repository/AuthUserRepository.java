package com.github.sparkzxl.authority.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.github.sparkzxl.authority.infrastructure.convert.AuthRoleConvert;
import com.github.sparkzxl.authority.infrastructure.convert.AuthUserConvert;
import com.github.sparkzxl.authority.infrastructure.entity.*;
import com.github.sparkzxl.authority.infrastructure.mapper.*;
import com.github.sparkzxl.database.annonation.InjectionResult;
import com.github.sparkzxl.authority.domain.repository.IAuthUserRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Repository
public class AuthUserRepository implements IAuthUserRepository {

    @Autowired
    public AuthUserMapper authUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AuthRoleMapper authRoleMapper;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private AuthResourceMapper authResourceMapper;
    @Autowired
    private AuthMenuMapper authMenuMapper;

    @Override
    public AuthUser selectById(Long id) {
        return authUserMapper.selectById(id);
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
    public void incrPasswordErrorNumById(Long id) {
        authUserMapper.incrPasswordErrorNumById(id);
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
    public void incrPasswordErrorNumByAccount(String account) {
        authUserMapper.incrPasswordErrorNumByAccount(account);
    }

    @Override
    public void deleteUserRelation(List<Long> ids) {
        userRoleMapper.delete(new LambdaUpdateWrapper<UserRole>().in(UserRole::getUserId, ids));
    }

    @Override
    public LoginAuthUser getLoginAuthUser(Long id) {
        AuthUser authUser = selectById(id);
        LoginAuthUser loginAuthUser = AuthUserConvert.INSTANCE.convertLoginAuthUser(authUser);
        List<Long> roleIds =
                userRoleMapper.selectList(new LambdaUpdateWrapper<UserRole>().eq(UserRole::getUserId, id)).stream().map(UserRole::getRoleId)
                        .collect(Collectors.toList());
        List<RoleAuthority> roleAuthorities =
                roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>().in(RoleAuthority::getRoleId, roleIds));
        List<Long> authorityIds = roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>().in(RoleAuthority::getRoleId,
                roleIds)).stream().filter(x -> "RESOURCE".equals(x.getAuthorityType()))
                .map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        Map<Long, Long> roleAuthorityIdMap =
                roleAuthorities.stream().collect(Collectors.toMap(RoleAuthority::getAuthorityId, RoleAuthority::getRoleId));
        List<AuthResource> resourceList = authResourceMapper.selectBatchIds(authorityIds);
        List<LoginPermission> loginPermissionList = Lists.newArrayList();
        Map<Long, List<LoginPermission>> loginPermissionMap = Maps.newHashMap();
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
}
