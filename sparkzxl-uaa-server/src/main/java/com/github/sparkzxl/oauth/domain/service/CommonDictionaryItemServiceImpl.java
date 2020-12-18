package com.github.sparkzxl.oauth.domain.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.sparkzxl.core.utils.MapHelper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.database.properties.InjectionProperties;
import com.github.sparkzxl.oauth.application.service.ICommonDictionaryItemService;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.oauth.infrastructure.mapper.CommonDictionaryItemMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: 字典项 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:43:58
 */
@Service
public class CommonDictionaryItemServiceImpl extends AbstractSuperCacheServiceImpl<CommonDictionaryItemMapper, CommonDictionaryItem> implements ICommonDictionaryItemService {

    @Autowired
    private InjectionProperties injectionProperties;

    @Override
    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
        if (codes.isEmpty()) {
            return Collections.emptyMap();
        }
        Set<String> types = codes.stream().filter(Objects::nonNull)
                .map((item) -> StrUtil.split(String.valueOf(item), injectionProperties.getDictSeparator())[0]).collect(Collectors.toSet());
        Set<String> newCodes = codes.stream().filter(Objects::nonNull)
                .map((item) -> StrUtil.split(String.valueOf(item), injectionProperties.getDictSeparator())[1]).collect(Collectors.toSet());

        // 1. 根据 字典编码查询可用的字典列表
        LambdaQueryWrapper<CommonDictionaryItem> dictionaryItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dictionaryItemLambdaQueryWrapper.in(CommonDictionaryItem::getDictionaryType, types)
                .in(CommonDictionaryItem::getCode, newCodes)
                .eq(CommonDictionaryItem::getStatus, true)
                .orderByAsc(CommonDictionaryItem::getSortValue);
        List<CommonDictionaryItem> list = super.list(dictionaryItemLambdaQueryWrapper);

        // 2. 将 list 转换成 Map，Map的key是字典编码，value是字典名称
        ImmutableMap<String, String> typeMap = MapHelper.uniqueIndex(list,
                (item) -> StrUtil.join(injectionProperties.getDictSeparator(), item.getDictionaryType(), item.getCode())
                , CommonDictionaryItem::getName);

        // 3. 将 Map<String, String> 转换成 Map<Serializable, Object>
        Map<Serializable, Object> typeCodeNameMap = new HashMap<>(typeMap.size());
        typeMap.forEach(typeCodeNameMap::put);
        return typeCodeNameMap;
    }

    @Override
    public List<CommonDictionaryItem> findDictionaryItemList(Long dictionaryId, String dictionaryType) {
        LambdaQueryWrapper<CommonDictionaryItem> dictionaryItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(dictionaryId)){
            dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getDictionaryId,dictionaryId);
        }
        if (StringUtils.isNotEmpty(dictionaryType)){
            dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getDictionaryType,dictionaryType);
        }
        dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getStatus, true)
                .orderByAsc(CommonDictionaryItem::getSortValue);
        return super.list(dictionaryItemLambdaQueryWrapper);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.DICTIONARY_ITEM;
    }
}
