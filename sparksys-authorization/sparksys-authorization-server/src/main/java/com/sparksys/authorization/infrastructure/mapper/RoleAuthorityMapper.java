package com.sparksys.authorization.infrastructure.mapper;

import com.sparksys.database.base.mapper.SuperMapper;
import com.sparksys.authorization.infrastructure.entity.RoleAuthority;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * description: 角色的资源 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:57:42
 */
@Repository
public interface RoleAuthorityMapper extends SuperMapper<RoleAuthority> {

    /**
     * 删除角色菜单
     *
     * @param id 角色id
     */
    @Delete("DELETE FROM c_auth_role_authority WHERE role_id = #{roleId}")
    void deleteByRoleId(@Param("roleId") Long id);
}
