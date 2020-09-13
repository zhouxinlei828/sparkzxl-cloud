package com.github.sparkzxl.oauth.infrastructure.mapper;

import com.github.sparkzxl.database.base.mapper.SuperMapper;
import com.github.sparkzxl.oauth.infrastructure.entity.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * description：系统日志
 *
 * @author zhouxinlei
 * @date 2020-06-17 11:56:18
 */
@Repository
public interface LoginLogMapper extends SuperMapper<LoginLog> {

}
