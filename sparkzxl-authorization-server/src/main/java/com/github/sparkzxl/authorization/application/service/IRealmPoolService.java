package com.github.sparkzxl.authorization.application.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.RealmPool;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.RealmPoolPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.RealmPoolSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.RealmPoolUpdateDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.database.dto.PageParams;

import java.util.List;

/**
 * description: 领域池信息 服务类
 *
 * @author charles.zhou
 * @date   2021-02-02 16:20:51
 */
public interface IRealmPoolService extends SuperCacheService<RealmPool> {

    /**
     * 分页查询领域池列表
     *
     * @param params 领域池分页查询参数
     * @return PageInfo<RealmPool>
     */
    PageInfo<RealmPool> getRealmPoolPageList(PageParams<RealmPoolPageDTO> params);

    /**
     * 保存领域池信息
     *
     * @param realmPoolSaveDTO 领域池保存对象
     * @return boolean
     */
    boolean saveRealmPool(RealmPoolSaveDTO realmPoolSaveDTO);

    /**
     * 更新领域池信息
     *
     * @param realmPoolUpdateDTO 领域池更新对象
     * @return boolean
     */
    boolean updateRealmPool(RealmPoolUpdateDTO realmPoolUpdateDTO);

    /**
     * 删除领域池
     *
     * @param realmPoolId 领域池id
     * @return boolean
     */
    boolean deleteRealmPool(Long realmPoolId);

    /**
     * 批量删除领域池
     * @param realmPoolIds 领域池ids
     * @return boolean
     */
    boolean deleteBatchRealmPool(List<Long> realmPoolIds);

    /**
     * check 领域池信息
     *
     * @param tenantCode 领域池code
     * @return boolean
     */
    boolean checkTenantCode(String tenantCode);
}
