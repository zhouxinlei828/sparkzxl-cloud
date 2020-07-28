package com.sparksys.oauth.domain.service;

import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.infrastructure.entity.CommonDictionary;
import com.sparksys.oauth.infrastructure.mapper.CommonDictionaryMapper;
import com.sparksys.oauth.application.service.ICommonDictionaryService;
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
