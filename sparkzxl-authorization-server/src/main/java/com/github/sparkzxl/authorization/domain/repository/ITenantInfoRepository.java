package com.github.sparkzxl.authorization.domain.repository;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;

import java.util.List;

/**
 * description: 领域池仓储类
 *
 * @author charles.zhou
 * @date   2021-02-14 10:11:05
 */
public interface ITenantInfoRepository {

    /**
     * 查询领域池列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     * @param code     领域池编码
     * @param name     领域池名称
     * @return PageInfo<TenantInfo>
     */
    PageInfo<TenantInfo> getTenantPageList(int pageNum, int pageSize, String code, String name);

    /**
     * 保存领域池信息
     *
     * @param tenant 领域池信息
     * @return boolean
     */
    boolean saveTenant(TenantInfo tenant);

    /**
     * 更新领域池信息
     *
     * @param tenant 领域池信息
     * @return boolean
     */
    boolean updateTenant(TenantInfo tenant);

    /**
     * 删除领域池信息
     *
     * @param tenantId 领域池id
     * @return boolean
     */
    boolean deleteTenant(Long tenantId);

    /**
     * 批量删除领域池信息
     *
     * @param tenantIds 领域池ids
     * @return boolean
     */
    boolean deleteBatchTenant(List<Long> tenantIds);
}
