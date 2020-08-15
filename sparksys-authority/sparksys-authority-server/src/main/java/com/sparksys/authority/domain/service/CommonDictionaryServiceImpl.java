package com.sparksys.authority.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authority.application.service.ICommonDictionaryService;
import com.sparksys.authority.infrastructure.entity.CommonDictionary;
import com.sparksys.authority.infrastructure.mapper.CommonDictionaryMapper;
import org.springframework.stereotype.Service;

/**
 * description: 字典类型 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:44:24
 */
@Service
public class CommonDictionaryServiceImpl extends AbstractSuperCacheServiceImpl<CommonDictionaryMapper, CommonDictionary> implements ICommonDictionaryService {

    @Override
    protected String getRegion() {
        return null;
    }
}
