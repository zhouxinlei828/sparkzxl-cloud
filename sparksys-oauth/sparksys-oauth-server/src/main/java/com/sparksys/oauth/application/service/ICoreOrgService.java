package com.sparksys.oauth.application.service;


import com.sparksys.database.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.CoreOrg;
import com.sparksys.oauth.interfaces.dto.org.OrgSaveDTO;
import com.sparksys.oauth.interfaces.dto.org.OrgUpdateDTO;

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
     * @param name 名称
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
     * @param userId       用户id
     * @param orgUpdateDTO 组织更新DTO
     * @return boolean
     */
    boolean updateCoreOrg(Long userId, OrgUpdateDTO orgUpdateDTO);

    boolean deleteCoreOrg(Long id);
}
