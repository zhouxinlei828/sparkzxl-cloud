package com.sparksys.authorization.domain.repository;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.infrastructure.po.AuthRoleDO;

/**
 * description: 角色 仓储类
 *
 * @Author zhouxinlei
 * @Date 2020-06-07 13:31:48
 */
public interface IAuthRoleRepository {

    Page<AuthRoleDO> listByPage(Page page, String name);

    AuthRoleDO getAuthRole(Long id);

    boolean saveAuthRole(AuthRoleDO authRoleDO);

    boolean updateAuthRole(AuthRoleDO authRoleDO);

    boolean deleteAuthRole(Long id);
}
