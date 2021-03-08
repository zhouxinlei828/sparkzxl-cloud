package com.github.sparkzxl.authorization.domain.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.sparkzxl.authorization.application.service.IRoleOrgService;
import com.github.sparkzxl.authorization.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authorization.infrastructure.entity.RoleOrg;
import com.github.sparkzxl.authorization.infrastructure.mapper.RoleOrgMapper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 角色组织关系 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:01:18
 */
@Service
public class RoleOrgServiceImpl extends AbstractSuperCacheServiceImpl<RoleOrgMapper, RoleOrg> implements IRoleOrgService {

    @Override
    public boolean deleteRoleOrgByOrgId(Long orgId) {
        return remove(new UpdateWrapper<RoleOrg>().lambda().eq(RoleOrg::getOrgId, orgId));
    }

    @Override
    public void deleteRoleOrgByOrgIds(List<Long> orgIds) {
        remove(new UpdateWrapper<RoleOrg>().lambda().in(RoleOrg::getOrgId, orgIds));
    }

    @Override
    protected String getRegion() {
        return CacheConstant.ROLE_ORG;
    }
}
