package com.sparksys.activiti.domain.repository;

import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.infrastructure.entity.ActReModel;

import java.util.List;

/**
 * description: 模型 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-25 11:16:57
 */
public interface IActReModelRepository {
    /**
     * 查询模型列表
     *
     * @param key  模型key
     * @param name 模型名称
     * @return PageInfo<ActReModel>
     */
    PageInfo<ActReModel> actReModelList(String key, String name);
}
