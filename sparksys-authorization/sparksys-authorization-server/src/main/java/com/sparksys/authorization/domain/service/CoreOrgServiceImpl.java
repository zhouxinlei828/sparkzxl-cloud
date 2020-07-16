package com.sparksys.authorization.domain.service;

import com.sparksys.core.constant.CacheKey;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.ICoreOrgService;
import com.sparksys.authorization.infrastructure.entity.CoreOrg;
import com.sparksys.authorization.infrastructure.mapper.CoreOrgMapper;
import org.springframework.stereotype.Service;

/**
 * description: 组织 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:37:19
 */
@Service
public class CoreOrgServiceImpl extends AbstractSuperCacheServiceImpl<CoreOrgMapper, CoreOrg> implements ICoreOrgService {

    @Override
    protected String getRegion() {
        return CacheKey.ORG;
    }
}
