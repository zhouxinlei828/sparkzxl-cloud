package com.github.sparkzxl.test.domain.service;

import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.test.application.service.IAuthUserService;
import com.github.sparkzxl.test.infrastructure.client.FileClient;
import com.github.sparkzxl.test.infrastructure.entity.AuthUser;
import com.github.sparkzxl.test.infrastructure.mapper.AuthUserMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FileClient fileClient;

    @Override
    @GlobalTransactional
    public boolean testSeataTx(AuthUser authUser) {
        log.info("[创建用户] 当前 XID: {}", RootContext.getXID());
        fileClient.saveFile("https://minio.en2hr.com/hongneng/image/images-icon_1586839683912.jpg");
        save(authUser);
        return true;
    }

    @Override
    protected String getRegion() {
        return "authuser";
    }
}
