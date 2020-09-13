package com.github.sparkzxl.authority.domain.service;

import com.github.sparkzxl.authority.domain.repository.IRoleAuthorityRepository;
import com.github.sparkzxl.authority.interfaces.dto.role.RoleAuthoritySaveDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.authority.application.service.IRoleAuthorityService;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authority.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.authority.infrastructure.mapper.RoleAuthorityMapper;
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
