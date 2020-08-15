package com.sparksys.authority.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sparksys.authority.domain.repository.IUserRoleRepository;
import com.sparksys.authority.infrastructure.entity.UserRole;
import com.sparksys.authority.infrastructure.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * description:账户角色绑定 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:19:03
 */
@Repository
public class UserRoleRepository implements IUserRoleRepository {

    private final UserRoleMapper userRoleMapper;

    public UserRoleRepository(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

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
}
