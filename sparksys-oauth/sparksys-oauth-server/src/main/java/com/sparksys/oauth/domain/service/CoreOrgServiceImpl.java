package com.sparksys.oauth.domain.service;

import com.sparksys.commons.core.cache.CacheKey;
import com.sparksys.commons.mybatis.service.impl.AbstractSuperCacheServiceImpl;
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
