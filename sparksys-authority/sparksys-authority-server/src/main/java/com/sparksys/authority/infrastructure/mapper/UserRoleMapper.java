package com.sparksys.authority.infrastructure.mapper;

import com.sparksys.database.base.mapper.SuperMapper;
import com.sparksys.authority.infrastructure.entity.UserRole;
import org.springframework.stereotype.Repository;

/**
 * description: 账号角色绑定 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:58:18
 */
@Repository
public interface UserRoleMapper extends SuperMapper<UserRole> {

}
