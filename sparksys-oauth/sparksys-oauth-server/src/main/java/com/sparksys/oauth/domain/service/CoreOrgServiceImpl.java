package com.sparksys.oauth.domain.service;

import com.sparksys.core.constant.CacheKey;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.application.service.ICoreOrgService;
import com.sparksys.oauth.infrastructure.entity.CoreOrg;
import com.sparksys.oauth.infrastructure.mapper.CoreOrgMapper;
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
