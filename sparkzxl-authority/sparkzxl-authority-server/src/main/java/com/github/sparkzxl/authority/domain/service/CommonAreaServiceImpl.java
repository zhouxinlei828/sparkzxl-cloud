package com.github.sparkzxl.authority.domain.service;

import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.authority.application.service.ICommonAreaService;
import com.github.sparkzxl.authority.infrastructure.entity.CommonArea;
import com.github.sparkzxl.authority.infrastructure.mapper.CommonAreaMapper;
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
