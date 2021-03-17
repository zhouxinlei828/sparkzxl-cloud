package com.github.sparkzxl.authorization.domain.service;

import com.github.sparkzxl.authorization.application.service.IDictionaryService;
import com.github.sparkzxl.authorization.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authorization.infrastructure.entity.CommonDictionary;
import com.github.sparkzxl.authorization.infrastructure.mapper.CommonDictionaryMapper;
import com.github.sparkzxl.database.base.service.impl.SuperCacheServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 字典类型 服务实现类
 *
 * @author charles.zhou
 * @date   2020-07-28 19:44:24
 */
@Service
public class DictionaryServiceImpl extends SuperCacheServiceImpl<CommonDictionaryMapper, CommonDictionary> implements IDictionaryService {

    @Override
    protected String getRegion() {
        return CacheConstant.DICTIONARY;
    }
}
