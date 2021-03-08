package com.github.sparkzxl.authorization.domain.service;

import com.github.sparkzxl.authorization.application.service.ICommonDictionaryService;
import com.github.sparkzxl.authorization.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authorization.infrastructure.entity.CommonDictionary;
import com.github.sparkzxl.authorization.infrastructure.mapper.CommonDictionaryMapper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
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
        return CacheConstant.DICTIONARY;
    }
}
