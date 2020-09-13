package com.github.sparkzxl.authority.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.sparkzxl.authority.domain.repository.IUserRoleRepository;
import com.github.sparkzxl.authority.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authority.infrastructure.entity.UserRole;
import com.github.sparkzxl.authority.infrastructure.mapper.AuthUserMapper;
import com.github.sparkzxl.authority.infrastructure.mapper.UserRoleMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description:账户角色绑定 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:19:03
 */
@Repository
public class UserRoleRepository implements IUserRoleRepository {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public boolean saveAuthRoleUser(Long id, Set<Serializable> userIds) {
        List<UserRole> userRoles = new ArrayList<>(userIds.size());
        userIds.forEach(userId -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(id);
            userRole.setUserId((Long) userId);
            userRoles.add(userRole);
        });
        return userRoleMapper.insertBatchSomeColumn(userRoles) > 0;
    }

    @Override
    public boolean deleteAuthRoleUser(Long id, Set<Serializable> userIds) {
        userIds.forEach(userId -> userRoleMapper.delete(new UpdateWrapper<UserRole>().lambda().eq(UserRole::getRoleId, id).eq(UserRole::getUserId, userId)));
        return true;
    }

    @Override
    public List<AuthUser> getRoleUserList(Long roleId) {
        List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getRoleId, roleId));
        List<Long> userIds = userRoles.stream().filter(x -> x.getUserId() != null).map(UserRole::getUserId).distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(userIds)) {
            return authUserMapper.selectBatchIds(userIds);
        }
        return null;
    }
}
