package com.sparksys.oauth.infrastructure.mapper;

import com.sparksys.database.mapper.SuperMapper;
import com.sparksys.oauth.infrastructure.entity.AuthRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;

/**
 * description: 角色 Mapper 接口
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:29:38
 */
@Repository
public interface AuthRoleMapper extends SuperMapper<AuthRole> {

}
