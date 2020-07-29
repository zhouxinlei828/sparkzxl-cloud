package com.sparksys.oauth.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.infrastructure.constant.CacheConstant;
import com.sparksys.oauth.infrastructure.entity.RoleAuthority;
import com.sparksys.oauth.infrastructure.mapper.RoleAuthorityMapper;
import com.sparksys.oauth.application.service.IRoleAuthorityService;
import org.springframework.stereotype.Service;

/**
 * description: 角色的资源 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:59:45
 */
@Service
public class RoleAuthorityServiceImpl extends AbstractSuperCacheServiceImpl<RoleAuthorityMapper, RoleAuthority> implements IRoleAuthorityService {

    @Override
    protected String getRegion() {
        return CacheConstant.ROLE_RESOURCE;
    }
}
