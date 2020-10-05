package com.github.sparkzxl.oauth.domain.service;

import com.github.sparkzxl.oauth.application.service.IAuthResourceService;
import com.github.sparkzxl.oauth.domain.repository.IAuthResourceRepository;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.entity.AuthResource;
import com.github.sparkzxl.oauth.infrastructure.mapper.AuthResourceMapper;
import com.github.sparkzxl.oauth.interfaces.dto.resource.ResourceQueryDTO;
import com.github.sparkzxl.core.utils.KeyUtils;
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
        String userResourceKey = KeyUtils.buildKey(getRegion(),userId);
        List<AuthResource> visibleResource = Lists.newArrayList();
        cacheTemplate.get(userResourceKey,(key) -> {
            visibleResource.addAll(authResourceRepository.findVisibleResource(resource.getUserId(),resource.getMenuId()));
            return visibleResource.stream().mapToLong(AuthResource::getId).boxed().collect(Collectors.toList());
        });
        return null;
    }
}
