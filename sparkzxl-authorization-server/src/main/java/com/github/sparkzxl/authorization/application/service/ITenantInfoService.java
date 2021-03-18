package com.github.sparkzxl.authorization.application.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.database.dto.PageParams;

import java.util.List;

/**
 * description: 领域池信息 服务类
 *
 * @author charles.zhou
 * @date   2021-02-02 16:20:51
 */
public interface ITenantInfoService extends SuperCacheService<TenantInfo> {

    /**
     * 分页查询领域池列表
     *
     * @param params 领域池分页查询参数
     * @return PageInfo<TenantInfo>
     */
    PageInfo<TenantInfo> getTenantPageList(PageParams<TenantPageDTO> params);

    /**
     * 保存领域池信息
     *
     * @param tenantSaveDTO 领域池保存对象
     * @return boolean
     */
    boolean saveTenant(TenantSaveDTO tenantSaveDTO);

    /**
     * 更新领域池信息
     *
     * @param tenantUpdateDTO 领域池更新对象
     * @return boolean
     */
    boolean updateTenant(TenantUpdateDTO tenantUpdateDTO);

    /**
     * 删除领域池
     *
     * @param tenantId 领域池id
     * @return boolean
     */
    boolean deleteTenant(Long tenantId);

    /**
     * 批量删除领域池
     * @param tenantIds 领域池ids
     * @return boolean
     */
    boolean deleteBatchTenant(List<Long> tenantIds);

    /**
     * check 领域池信息
     *
     * @param tenantCode 领域池code
     * @return boolean
     */
    boolean checkTenantCode(String tenantCode);
}
