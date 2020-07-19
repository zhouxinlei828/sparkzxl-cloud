package com.sparksys.oauth.domain.service;

import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.infrastructure.entity.RoleOrg;
import com.sparksys.oauth.infrastructure.mapper.RoleOrgMapper;
import com.sparksys.oauth.application.service.IRoleOrgService;
import org.springframework.stereotype.Service;

/**
 * description: 角色组织关系 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:01:18
 */
@Service
public class RoleOrgServiceImpl extends AbstractSuperCacheServiceImpl<RoleOrgMapper, RoleOrg> implements IRoleOrgService {
    @Override
    protected String getRegion() {
        return null;
    }
}
