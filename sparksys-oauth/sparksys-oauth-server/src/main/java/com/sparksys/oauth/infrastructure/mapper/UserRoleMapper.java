package com.sparksys.oauth.infrastructure.mapper;

import com.sparksys.database.mapper.SuperMapper;
import com.sparksys.oauth.infrastructure.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * description: 账号角色绑定 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:58:18
 */
@Repository
public interface UserRoleMapper extends SuperMapper<UserRole> {

    void deleteByRoleId(@Param("roleId") Long id);
}
