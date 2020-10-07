package com.github.sparkzxl.oauth.application.service;


import com.github.sparkzxl.oauth.infrastructure.entity.CoreOrg;
import com.github.sparkzxl.oauth.interfaces.dto.org.OrgSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.org.OrgUpdateDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;

import java.util.List;

/**
 * description: 组织 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:32:24
 */
public interface ICoreOrgService extends SuperCacheService<CoreOrg> {

    /**
     * 查询组织列表
     *
     * @param name   名称
     * @param status 状态
     * @return List<CoreOrg>
     */
    List<CoreOrg> getCoreOrgList(String name, Boolean status);

    /**
     * 新增组织
     *
     * @param userId     用户id
     * @param orgSaveDTO 组织保存DTO
     * @return boolean
     */
    boolean saveCoreOrg(Long userId, OrgSaveDTO orgSaveDTO);

    /**
     * 修改组织
     *
     * @param orgUpdateDTO 组织更新DTO
     * @return boolean
     */
    boolean updateCoreOrg(OrgUpdateDTO orgUpdateDTO);

    /**
     * 删除组织
     *
     * @param id 组织id
     * @return boolean
     */
    boolean deleteCoreOrg(Long id);

    /**
     * 批量删除组织
     *
     * @param ids 组织id列表
     * @return
     */
    boolean deleteBatchCoreOrg(List<Long> ids);
}
