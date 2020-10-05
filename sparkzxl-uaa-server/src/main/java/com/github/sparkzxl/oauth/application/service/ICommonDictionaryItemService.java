package com.github.sparkzxl.oauth.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface ICommonDictionaryItemService extends IService<CommonDictionaryItem> {

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
     * @param dictionaryType 字典类型
     * @return List<CommonDictionaryItem>
     */
    List<CommonDictionaryItem> findDictionaryItemByDictionaryType(String dictionaryType);
}
