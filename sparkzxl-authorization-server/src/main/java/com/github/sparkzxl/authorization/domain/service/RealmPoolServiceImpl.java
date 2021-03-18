package com.github.sparkzxl.authorization.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.IRealmPoolService;
import com.github.sparkzxl.authorization.domain.repository.IRealmPoolRepository;
import com.github.sparkzxl.authorization.infrastructure.convert.TenantConvert;
import com.github.sparkzxl.authorization.infrastructure.entity.RealmPool;
import com.github.sparkzxl.authorization.infrastructure.mapper.RealmPoolMapper;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.RealmPoolPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.RealmPoolSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.RealmPoolUpdateDTO;
import com.github.sparkzxl.database.base.service.impl.SuperCacheServiceImpl;
import com.github.sparkzxl.database.dto.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 领域池信息 服务实现类
 *
 * @author charles.zhou
 * @date 2021-02-02 16:21:08
 */
@Service
public class RealmPoolServiceImpl extends SuperCacheServiceImpl<RealmPoolMapper, RealmPool> implements IRealmPoolService {

    @Autowired
    private IRealmPoolRepository tenantRepository;

    @Override
    public PageInfo<RealmPool> getRealmPoolPageList(PageParams<RealmPoolPageDTO> params) {
        return tenantRepository.getRealmPoolPageList(params.getPageNum(), params.getPageSize(), params.getModel().getCode(),
                params.getModel().getName());
    }

    @Override
    public boolean saveRealmPool(RealmPoolSaveDTO realmPoolSaveDTO) {
        RealmPool tenant = TenantConvert.INSTANCE.convertRealmPool(realmPoolSaveDTO);
        return tenantRepository.saveRealmPool(tenant);
    }

    @Override
    public boolean updateRealmPool(RealmPoolUpdateDTO realmPoolUpdateDTO) {
        RealmPool tenant = TenantConvert.INSTANCE.convertRealmPool(realmPoolUpdateDTO);
        return tenantRepository.updateRealmPool(tenant);
    }

    @Override
    public boolean deleteRealmPool(Long realmPoolId) {
        return tenantRepository.deleteRealmPool(realmPoolId);
    }

    @Override
    public boolean deleteBatchRealmPool(List<Long> realmPoolIds) {
        return tenantRepository.deleteBatchRealmPool(realmPoolIds);
    }

    @Override
    public boolean checkTenantCode(String tenantCode) {
        return count(new LambdaQueryWrapper<RealmPool>().eq(RealmPool::getCode, tenantCode)) == 1;
    }

    @Override
    protected String getRegion() {
        return "realm_pool";
    }
}
