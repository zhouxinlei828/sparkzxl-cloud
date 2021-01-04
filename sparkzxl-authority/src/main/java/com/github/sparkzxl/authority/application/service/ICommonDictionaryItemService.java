package com.github.sparkzxl.authority.application.service;

import com.github.sparkzxl.authority.infrastructure.entity.CoreStation;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.authority.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.authority.interfaces.dto.dictionary.DictionaryItemQueryDTO;

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
     * @param dictionaryItemQueryDTO   字典项查询入参
     * @return List<CommonDictionaryItem>
     */
    List<CommonDictionaryItem> findDictionaryItemList(DictionaryItemQueryDTO dictionaryItemQueryDTO);

    /**
     * 根据名称查询字典项信息
     * @param name 名称
     * @return CommonDictionaryItem
     */
    CommonDictionaryItem getDictionaryItemByName(String name);
}
