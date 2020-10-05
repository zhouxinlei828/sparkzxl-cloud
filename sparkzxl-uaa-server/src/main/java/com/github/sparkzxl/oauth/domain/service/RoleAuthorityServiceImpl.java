package com.github.sparkzxl.oauth.domain.service;

import com.github.sparkzxl.oauth.application.service.IRoleAuthorityService;
import com.github.sparkzxl.oauth.domain.repository.IRoleAuthorityRepository;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.oauth.infrastructure.mapper.RoleAuthorityMapper;
import com.github.sparkzxl.oauth.interfaces.dto.role.RoleAuthoritySaveDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 角色的资源 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:59:45
 */
@Service
public class RoleAuthorityServiceImpl extends AbstractSuperCacheServiceImpl<RoleAuthorityMapper, RoleAuthority> implements IRoleAuthorityService {

    @Autowired
    private IRoleAuthorityRepository authorityRepository;

    @Override
    protected String getRegion() {
        return CacheConstant.ROLE_RESOURCE;
    }

    @Override
    public boolean saveRoleAuthorityBatch(RoleAuthoritySaveDTO roleAuthoritySaveDTO) {
        return authorityRepository.saveRoleAuthorityBatch(roleAuthoritySaveDTO.getRoleId(),roleAuthoritySaveDTO.getAuthorityType(),roleAuthoritySaveDTO.getAuthorityList());
    }
}
