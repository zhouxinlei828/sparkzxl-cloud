package com.github.sparkzxl.authority.domain.service;

import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.authority.application.service.ICommonDictionaryItemService;
import com.github.sparkzxl.authority.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.authority.infrastructure.mapper.CommonDictionaryItemMapper;
import org.springframework.stereotype.Service;

/**
 * description: 字典项 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:43:58
 */
@Service
public class CommonDictionaryItemServiceImpl extends AbstractSuperCacheServiceImpl<CommonDictionaryItemMapper, CommonDictionaryItem> implements ICommonDictionaryItemService {

    @Override
    protected String getRegion() {
        return null;
    }
}
