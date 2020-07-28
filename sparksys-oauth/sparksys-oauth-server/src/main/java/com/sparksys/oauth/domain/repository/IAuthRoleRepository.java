package com.sparksys.oauth.domain.repository;


import com.github.pagehelper.PageInfo;
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

    /**
     * 根据角色分页查询列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     * @param name     角色名称
     * @return PageInfo<AuthRole>
     */
    PageInfo<AuthRole> listByName(int pageNum, int pageSize, String name);

    /**
     * 删除角色以及关联信息
     *
     * @param id 角色id
     * @return boolean
     */
    boolean deleteAuthRole(Long id);
}
