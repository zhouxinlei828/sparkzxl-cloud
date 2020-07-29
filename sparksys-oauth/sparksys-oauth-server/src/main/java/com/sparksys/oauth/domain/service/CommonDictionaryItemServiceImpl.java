package com.sparksys.oauth.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.infrastructure.entity.CommonDictionaryItem;
import com.sparksys.oauth.infrastructure.mapper.CommonDictionaryItemMapper;
import com.sparksys.oauth.application.service.ICommonDictionaryItemService;
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
