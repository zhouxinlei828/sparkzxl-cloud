package com.sparksys.oauth.infrastructure.mapper;

import com.sparksys.database.mapper.SuperMapper;
import com.sparksys.oauth.infrastructure.entity.RoleAuthority;
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

    void deleteByRoleId(@Param("roleId") Long id);
}
