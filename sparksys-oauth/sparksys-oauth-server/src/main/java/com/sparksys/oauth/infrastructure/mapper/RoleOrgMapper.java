package com.sparksys.oauth.infrastructure.mapper;

import com.sparksys.database.mapper.SuperMapper;
import com.sparksys.oauth.infrastructure.entity.RoleOrg;
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

    void deleteByRoleId(@Param("roleId") Long id);
}
