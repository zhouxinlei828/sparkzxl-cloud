package com.sparksys.authorization.domain.repository;


/**
 * description: 角色 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:48
 */
public interface IAuthRoleRepository {

    /**
     * 删除角色以及关联信息
     *
     * @param id 角色id
     * @return boolean
     */
    boolean deleteAuthRole(Long id);
}
