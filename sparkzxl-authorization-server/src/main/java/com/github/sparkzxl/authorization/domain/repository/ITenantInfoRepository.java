package com.github.sparkzxl.authorization.domain.repository;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;

import java.util.List;

/**
 * description: 租户仓储类
 *
 * @author: zhouxinlei
 * @date: 2021-02-14 10:11:05
 */
public interface ITenantInfoRepository {

    /**
     * 查询租户列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     * @param code     租户编码
     * @param name     租户名称
     * @return PageInfo<TenantInfo>
     */
    PageInfo<TenantInfo> getTenantPageList(int pageNum, int pageSize, String code, String name);

    /**
     * 保存租户信息
     *
     * @param tenant 租户信息
     * @return boolean
     */
    boolean saveTenant(TenantInfo tenant);

    /**
     * 更新租户信息
     *
     * @param tenant 租户信息
     * @return boolean
     */
    boolean updateTenant(TenantInfo tenant);

    /**
     * 删除租户信息
     *
     * @param tenantId 租户id
     * @return boolean
     */
    boolean deleteTenant(Long tenantId);

    /**
     * 批量删除租户信息
     *
     * @param tenantIds 租户ids
     * @return boolean
     */
    boolean deleteBatchTenant(List<Long> tenantIds);
}
