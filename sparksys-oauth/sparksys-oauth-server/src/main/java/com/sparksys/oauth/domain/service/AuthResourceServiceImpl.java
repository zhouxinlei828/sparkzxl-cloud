package com.sparksys.oauth.domain.service;

import com.sparksys.core.utils.KeyUtils;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.application.service.IAuthResourceService;
import com.sparksys.oauth.domain.repository.IAuthResourceRepository;
import com.sparksys.oauth.infrastructure.constant.CacheConstant;
import com.sparksys.oauth.infrastructure.convert.AuthResourceConvert;
import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.oauth.infrastructure.mapper.AuthResourceMapper;
import com.sparksys.oauth.interfaces.dto.resource.AuthResourceDTO;
import com.sparksys.oauth.interfaces.dto.resource.ResourceQueryDTO;
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
    public List<AuthResourceDTO> resourceList() {
        List<AuthResource> authResources = authResourceRepository.authResourceList();
        return authResources.stream().map(AuthResourceConvert.INSTANCE::convertAuthResourceDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuthResource> findVisibleResource(Long userId, ResourceQueryDTO resource) {
        String userResourceKey = KeyUtils.buildKey(getRegion(),userId);
        List<AuthResource> visibleResource = new ArrayList<>();
        cacheRepository.get(userResourceKey,(key) -> {
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
