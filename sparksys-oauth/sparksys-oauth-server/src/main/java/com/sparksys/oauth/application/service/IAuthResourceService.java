package com.sparksys.oauth.application.service;


import com.sparksys.database.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.oauth.interfaces.dto.resource.AuthResourceDTO;
import com.sparksys.oauth.interfaces.dto.resource.ResourceQueryDTO;

import java.util.List;

/**
 * description: 资源 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:28
 */
public interface IAuthResourceService extends SuperCacheService<AuthResource> {

    /**
     * 查询资源列表
     *
     * @return
     */
    List<AuthResourceDTO> resourceList();

    /**
     * 查询用户可用的所有资源
     *
     * @param userId   用户id
     * @param resource 资源查询对象
     * @return
     */
    List<AuthResource> findVisibleResource(Long userId, ResourceQueryDTO resource);
}
