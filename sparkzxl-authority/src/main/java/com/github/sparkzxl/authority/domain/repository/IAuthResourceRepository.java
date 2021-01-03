package com.github.sparkzxl.authority.domain.repository;


import com.github.sparkzxl.authority.infrastructure.entity.AuthResource;

import java.util.List;

/**
 * description: 资源 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:28
 */
public interface IAuthResourceRepository {

    /**
     * 加载所有资源
     *
     * @return List<AuthResource>
     */
    List<AuthResource> authResourceList();


    /**
     * 获取用户资源
     *
     * @param userId
     * @param menuId
     * @return List<AuthResource>
     */
    List<AuthResource> findVisibleResource(Long userId, Long menuId);
}
