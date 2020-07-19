package com.sparksys.oauth.domain.repository;

import java.io.Serializable;
import java.util.Set;

/**
 * description: 账户角色绑定 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:12:43
 */
public interface IUserRoleRepository {

    /**
     * 保存角色用户
     *
     * @param id
     * @param userIds
     * @return
     */
    boolean saveAuthRoleUser(Long id, Set<Serializable> userIds);

    /**
     * 删除角色用户
     *
     * @param id
     * @param userIds
     * @return
     */
    boolean deleteAuthRoleUser(Long id, Set<Serializable> userIds);
}
