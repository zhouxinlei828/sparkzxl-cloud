package com.sparksys.authorization.domain.repository;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.infrastructure.entity.AuthRole;

/**
 * description: 角色 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleRepository {

    Page<AuthRole> listByPage(Page page, String name);

    AuthRole getAuthRole(Long id);

    boolean saveAuthRole(AuthRole authRole);

    boolean updateAuthRole(AuthRole authRole);

    boolean deleteAuthRole(Long id);
}
