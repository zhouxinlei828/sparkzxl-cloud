package com.sparksys.oauth.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sparksys.oauth.infrastructure.entity.AuthRole;
import org.springframework.stereotype.Repository;

/**
 * description: 角色 Mapper 接口
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:29:38
 */
@Repository
public interface AuthRoleMapper extends BaseMapper<AuthRole> {

    /**
     * 删除角色用户关系
     *
     * @param roleId
     * @return
     */
    int deleteUserRoleRelation(Long roleId);

    /**
     * 删除角色资源
     *
     * @param roleId
     * @return
     */
    int deleteRoleAuthorityRelation(Long roleId);


    /**
     * 删除角色组织关系
     *
     * @param roleId
     * @return
     */
    int deleteRoleOrgRelation(Long roleId);

}
