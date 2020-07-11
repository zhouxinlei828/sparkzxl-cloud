package com.sparksys.authorization.domain.repository;


import com.sparksys.authorization.infrastructure.entity.AuthRole;

import java.util.List;

/**
 * description: 角色 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleRepository {

    List<AuthRole> listByName(String name);

    AuthRole getAuthRole(Long id);

    boolean saveAuthRole(AuthRole authRole);

    boolean updateAuthRole(AuthRole authRole);

    boolean deleteAuthRole(Long id);
}
