package com.sparksys.authorization.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.IRoleAuthorityService;
import com.sparksys.authorization.infrastructure.constant.CacheConstant;
import com.sparksys.authorization.infrastructure.entity.RoleAuthority;
import com.sparksys.authorization.infrastructure.mapper.RoleAuthorityMapper;
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
