package com.sparksys.oauth.domain.repository;


import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * description: 岗位 仓储类
 *
 * @author zhouxinlei
 * @date  2020-06-07 13:32:55
 */
public interface ICoreStationRepository {

    /**
     * 根据id 查询 岗位名称
     *
     * @param ids
     * @return
     */
    Map<Serializable, Object> findStationNameByIds(Set<Serializable> ids);

}
