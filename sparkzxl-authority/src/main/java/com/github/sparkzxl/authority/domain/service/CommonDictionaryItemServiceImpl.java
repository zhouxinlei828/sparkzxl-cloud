package com.github.sparkzxl.authority.domain.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.sparkzxl.core.utils.MapHelper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.database.properties.InjectionProperties;
import com.github.sparkzxl.authority.application.service.ICommonDictionaryItemService;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authority.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.authority.infrastructure.mapper.CommonDictionaryItemMapper;
import com.github.sparkzxl.authority.interfaces.dto.dictionary.DictionaryItemQueryDTO;
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
    public List<CommonDictionaryItem> findDictionaryItemList(DictionaryItemQueryDTO dictionaryItemQueryDTO) {
        LambdaQueryWrapper<CommonDictionaryItem> dictionaryItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(dictionaryItemQueryDTO.getDictionaryId())){
            dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getDictionaryId,dictionaryItemQueryDTO.getDictionaryId());
        }
        if (StringUtils.isNotEmpty(dictionaryItemQueryDTO.getDictionaryType())){
            dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getDictionaryType,dictionaryItemQueryDTO.getDictionaryType());
        }
        if (StringUtils.isNotEmpty(dictionaryItemQueryDTO.getCode())){
            dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getCode,dictionaryItemQueryDTO.getCode());
        }
        if (StringUtils.isNotEmpty(dictionaryItemQueryDTO.getName())){
            dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getName,dictionaryItemQueryDTO.getName());
        }
        dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getStatus, true)
                .orderByAsc(CommonDictionaryItem::getSortValue);
        return super.list(dictionaryItemLambdaQueryWrapper);
    }

    @Override
    public CommonDictionaryItem getDictionaryItemByName(String name) {
        LambdaQueryWrapper<CommonDictionaryItem> dictionaryItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getName,name);
        dictionaryItemLambdaQueryWrapper.eq(CommonDictionaryItem::getStatus, true)
                .last("limit 1");
        return getOne(dictionaryItemLambdaQueryWrapper);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.DICTIONARY_ITEM;
    }
}
