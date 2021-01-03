package com.github.sparkzxl.authority.domain.service;

import com.github.sparkzxl.core.utils.BuildKeyUtils;
import com.github.sparkzxl.authority.application.service.IAuthResourceService;
import com.github.sparkzxl.authority.domain.repository.IAuthResourceRepository;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authority.infrastructure.entity.AuthResource;
import com.github.sparkzxl.authority.infrastructure.mapper.AuthResourceMapper;
import com.github.sparkzxl.authority.interfaces.dto.resource.ResourceQueryDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

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
    protected String getRegion() {
        return CacheConstant.RESOURCE;
    }

    @Override
    public List<AuthResource> findVisibleResource(Long userId, ResourceQueryDTO resource) {
        String userResourceKey = BuildKeyUtils.generateKey(getRegion(),userId);
        List<AuthResource> visibleResource = Lists.newArrayList();
        cacheTemplate.get(userResourceKey,(key) -> {
            visibleResource.addAll(authResourceRepository.findVisibleResource(resource.getUserId(),resource.getMenuId()));
            return visibleResource.stream().mapToLong(AuthResource::getId).boxed().collect(Collectors.toList());
        });
        return null;
    }
}
