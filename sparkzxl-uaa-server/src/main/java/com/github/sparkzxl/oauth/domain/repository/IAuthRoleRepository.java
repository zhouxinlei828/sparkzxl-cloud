package com.github.sparkzxl.oauth.domain.repository;


import java.util.List;

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
     * @param ids ids
     */
    void deleteAuthRoleRelation(List<Long> ids);
}
