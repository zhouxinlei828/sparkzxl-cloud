package com.github.sparkzxl.authorization.domain.repository;


import com.github.sparkzxl.authorization.infrastructure.entity.CoreOrg;
import com.github.sparkzxl.authorization.infrastructure.entity.CoreStation;
import com.github.sparkzxl.database.entity.RemoteData;

import java.io.Serializable;
import java.util.List;
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
     * 注入分页查询岗位列表
     *
     * @param pageNum  当前页
     * @param pageSize 分页大小
     * @param name     岗位名称
     * @param org      组织id
     * @return List<CoreStation>
     */
    List<CoreStation> getStationPageList(int pageNum, int pageSize, String name, RemoteData<Long, CoreOrg> org);
}
