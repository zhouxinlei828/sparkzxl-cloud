package com.github.sparkzxl.authorization.domain.repository;

import com.github.sparkzxl.authorization.infrastructure.entity.CommonDictionaryItem;

import java.util.Map;
import java.util.Set;

/**
 * description: 字典项仓储类
 *
 * @author: zhouxinlei
 * @date: 2021-03-06 20:55:19
 */
public interface IDictionaryItemRepository {

    /**
     * 查询字典项列表
     *
     * @param dictionaryType 字典类型
     * @param codes          字典code
     * @return Map<String, CommonDictionaryItem>
     */
    Map<String, CommonDictionaryItem> findDictionaryItemList(String dictionaryType, Set<String> codes);

}
