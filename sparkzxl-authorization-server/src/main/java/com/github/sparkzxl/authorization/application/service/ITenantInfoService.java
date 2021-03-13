package com.github.sparkzxl.authorization.application.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.database.dto.DeleteDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * description: 租户信息 服务类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 16:20:51
 */
public interface ITenantInfoService extends IService<TenantInfo> {

    /**
     * 分页查询租户列表
     *
     * @param tenantPageDTO 租户分页查询参数
     * @return PageInfo<TenantInfo>
     */
    PageInfo<TenantInfo> getTenantPageList(TenantPageDTO tenantPageDTO);

    /**
     * 保存租户信息
     *
     * @param tenantSaveDTO 租户保存对象
     * @return boolean
     */
    boolean saveTenant(TenantSaveDTO tenantSaveDTO);

    /**
     * 更新租户信息
     *
     * @param tenantUpdateDTO 租户更新对象
     * @return boolean
     */
    boolean updateTenant(TenantUpdateDTO tenantUpdateDTO);

    /**
     * 删除租户
     *
     * @param tenantId 租户id
     * @return boolean
     */
    boolean deleteTenant(Long tenantId);

    /**
     * 批量删除租户
     * @param tenantIds 租户ids
     * @return boolean
     */
    boolean deleteBatchTenant(List<Long> tenantIds);

    /**
     * check 租户信息
     *
     * @param tenantCode 租户code
     * @return boolean
     */
    boolean checkTenantCode(String tenantCode);
}
