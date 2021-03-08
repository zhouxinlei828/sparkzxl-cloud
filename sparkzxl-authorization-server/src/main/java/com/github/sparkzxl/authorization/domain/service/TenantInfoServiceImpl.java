package com.github.sparkzxl.authorization.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.ITenantInfoService;
import com.github.sparkzxl.authorization.domain.repository.ITenantInfoRepository;
import com.github.sparkzxl.authorization.infrastructure.convert.TenantConvert;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.authorization.infrastructure.mapper.TenantInfoMapper;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 租户信息 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 16:21:08
 */
@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {

    @Autowired
    private ITenantInfoRepository tenantRepository;

    @Override
    public PageInfo<TenantInfo> getTenantPageList(TenantPageDTO tenantPageDTO) {
        return tenantRepository.getTenantPageList(tenantPageDTO.getPageNum(), tenantPageDTO.getPageSize(), tenantPageDTO.getCode(),
                tenantPageDTO.getName());
    }

    @Override
    public boolean saveTenant(TenantSaveDTO tenantSaveDTO) {
        TenantInfo tenant = TenantConvert.INSTANCE.convertTenant(tenantSaveDTO);
        return tenantRepository.saveTenant(tenant);
    }

    @Override
    public boolean updateTenant(TenantUpdateDTO tenantUpdateDTO) {
        TenantInfo tenant = TenantConvert.INSTANCE.convertTenant(tenantUpdateDTO);
        return tenantRepository.updateTenant(tenant);
    }

    @Override
    public boolean deleteTenant(Long tenantId) {
        return tenantRepository.deleteTenant(tenantId);
    }

    @Override
    public boolean checkTenantCode(String tenantCode) {
        return count(new LambdaQueryWrapper<TenantInfo>().eq(TenantInfo::getCode, tenantCode)) == 1;
    }
}
