package com.sparksys.authorization.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.ICommonAreaService;
import com.sparksys.authorization.infrastructure.entity.CommonArea;
import com.sparksys.authorization.infrastructure.mapper.CommonAreaMapper;
import org.springframework.stereotype.Service;

/**
 * description: 地区表 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:43:36
 */
@Service
public class CommonAreaServiceImpl extends AbstractSuperCacheServiceImpl<CommonAreaMapper, CommonArea> implements ICommonAreaService {

    @Override
    protected String getRegion() {
        return null;
    }
}
