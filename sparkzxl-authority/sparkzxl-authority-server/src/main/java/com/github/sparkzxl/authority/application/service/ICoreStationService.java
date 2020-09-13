package com.github.sparkzxl.authority.application.service;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.authority.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authority.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.authority.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.authority.interfaces.dto.station.StationUpdateDTO;

/**
 * description: 岗位 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:32:55
 */
public interface ICoreStationService extends SuperCacheService<CoreStation> {

    /**
     * 查询岗位列表
     *
     * @param stationPageDTO 岗位分页查询对象
     * @return PageInfo<CoreStation>
     */
    PageInfo<CoreStation> getStationPageList(StationPageDTO stationPageDTO);

    /**
     * 新增岗位
     *
     * @param userId         用户id
     * @param stationSaveDTO 岗位保存对象
     * @return boolean
     */
    boolean saveCoreStation(Long userId, StationSaveDTO stationSaveDTO);

    /**
     * 修改岗位
     *
     * @param userId           用户id
     * @param stationUpdateDTO 岗位更新对象
     * @return boolean
     */
    boolean updateCoreStation(Long userId, StationUpdateDTO stationUpdateDTO);
}
