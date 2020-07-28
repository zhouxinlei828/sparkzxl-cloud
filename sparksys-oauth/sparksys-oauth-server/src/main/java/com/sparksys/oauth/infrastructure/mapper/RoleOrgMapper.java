package com.sparksys.oauth.infrastructure.mapper;

import com.sparksys.database.mapper.SuperMapper;
import com.sparksys.oauth.infrastructure.entity.RoleOrg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * description: 角色组织关系 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:58:57
 */
@Repository
public interface RoleOrgMapper extends SuperMapper<RoleOrg> {

    /**
     * 删除角色关联组织
     *
     * @param id 角色id
     */
    @Delete("DELETE FROM c_auth_role_org WHERE role_id = #{roleId}")
    void deleteByRoleId(@Param("roleId") Long id);
}
