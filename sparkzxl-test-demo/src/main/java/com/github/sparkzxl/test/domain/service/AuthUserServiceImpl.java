package com.github.sparkzxl.test.domain.service;

import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.test.application.service.IAuthUserService;
import com.github.sparkzxl.test.infrastructure.entity.AuthUser;
import com.github.sparkzxl.test.infrastructure.mapper.AuthUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description: 用户查询 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:57
 */
@Service
@Slf4j
public class AuthUserServiceImpl extends AbstractSuperCacheServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

    @Override
    protected String getRegion() {
        return "authuser";
    }
}
