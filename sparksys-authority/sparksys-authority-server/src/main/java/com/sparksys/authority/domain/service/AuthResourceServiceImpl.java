package com.sparksys.authority.domain.service;

import com.sparksys.core.utils.KeyUtils;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authority.application.service.IAuthResourceService;
import com.sparksys.authority.domain.repository.IAuthResourceRepository;
import com.sparksys.authority.infrastructure.constant.CacheConstant;
import com.sparksys.authority.infrastructure.entity.AuthResource;
import com.sparksys.authority.infrastructure.mapper.AuthResourceMapper;
import com.sparksys.authority.interfaces.dto.resource.ResourceQueryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 资源 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:36:15
 */
@Service
public class AuthResourceServiceImpl extends AbstractSuperCacheServiceImpl<AuthResourceMapper, AuthResource> implements IAuthResourceService {

    private final IAuthResourceRepository authResourceRepository;

    public AuthResourceServiceImpl(IAuthResourceRepository authResourceRepository) {
        this.authResourceRepository = authResourceRepository;
    }

    @Override
    public List<AuthResource> findVisibleResource(Long userId, ResourceQueryDTO resource) {
        String userResourceKey = KeyUtils.buildKey(getRegion(),userId);
        List<AuthResource> visibleResource = new ArrayList<>();
        cacheTemplate.get(userResourceKey,(key) -> {
            visibleResource.addAll(authResourceRepository.findVisibleResource(resource.getUserId(),resource.getMenuId()));
            return visibleResource.stream().mapToLong(AuthResource::getId).boxed().collect(Collectors.toList());
        });
        return null;
    }

    @Override
    protected String getRegion() {
        return CacheConstant.RESOURCE;
    }
}
