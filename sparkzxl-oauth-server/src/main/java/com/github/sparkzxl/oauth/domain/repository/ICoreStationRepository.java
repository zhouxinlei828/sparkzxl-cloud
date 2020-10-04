package com.github.sparkzxl.oauth.domain.repository;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.oauth.infrastructure.entity.CoreStation;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * description: 岗位 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:32:55
 */
public interface ICoreStationRepository {

    /**
     * 根据id 查询 岗位名称
     *
     * @param ids
     * @return
     */
    Map<Serializable, Object> findStationByIds(Set<Serializable> ids);

    /**
     * 分页查询岗位列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     * @param name     岗位名称
     * @param orgId    组织id
     * @return PageInfo<CoreStation>
     */
    PageInfo<CoreStation> getStationPageList(int pageNum, int pageSize, String name, Long orgId);
}
