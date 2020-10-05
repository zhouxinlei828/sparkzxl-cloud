package com.github.sparkzxl.oauth.infrastructure.mapper;

import com.github.sparkzxl.oauth.infrastructure.entity.UserRole;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
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
