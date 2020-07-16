package com.sparksys.authorization.infrastructure.mapper;

import com.sparksys.database.mapper.SuperMapper;
import com.sparksys.authorization.infrastructure.entity.AuthResource;
import org.springframework.stereotype.Repository;

/**
 * description: 资源 Mapper 接口
 *
 * @author zhouxinlei
 * @date  2020-06-07 13:29:12
 */
@Repository
public interface AuthResourceMapper extends SuperMapper<AuthResource> {

}
