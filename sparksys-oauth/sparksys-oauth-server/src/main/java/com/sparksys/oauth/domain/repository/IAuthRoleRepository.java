package com.sparksys.oauth.domain.repository;


import com.sparksys.oauth.infrastructure.entity.AuthRole;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
