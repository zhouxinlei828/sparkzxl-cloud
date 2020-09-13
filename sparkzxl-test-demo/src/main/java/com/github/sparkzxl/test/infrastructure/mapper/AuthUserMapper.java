package com.github.sparkzxl.test.infrastructure.mapper;

import com.github.sparkzxl.database.base.mapper.SuperMapper;
import com.github.sparkzxl.test.infrastructure.entity.AuthUser;
import org.springframework.stereotype.Repository;

/**
 * description: 用户 Mapper 接口
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:23:50
 */
@Repository
public interface AuthUserMapper extends SuperMapper<AuthUser> {

}
