package com.github.sparkzxl.oauth.domain.service;

import com.github.sparkzxl.oauth.application.service.ICommonAreaService;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonArea;
import com.github.sparkzxl.oauth.infrastructure.mapper.CommonAreaMapper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
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
