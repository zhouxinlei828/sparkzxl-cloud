package com.github.sparkzxl.oauth.application.service;

import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonDictionaryItem;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description: 字典项 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:42:04
 */
public interface ICommonDictionaryItemService extends SuperCacheService<CommonDictionaryItem> {

    /**
     * 根据类型编码查询字典项
     *
     * @param codes 字典编码
     * @return Map<Serializable, Object>
     */
    Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes);

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictionaryId   字典类型id
     * @param dictionaryType 字典类型
     * @return List<CommonDictionaryItem>
     */
    List<CommonDictionaryItem> findDictionaryItemList(Long dictionaryId, String dictionaryType);
}
