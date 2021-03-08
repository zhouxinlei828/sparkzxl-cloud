package com.github.sparkzxl.authorization.application.service;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationUpdateDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;

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
     * @param stationSaveDTO 岗位保存对象
     * @return boolean
     */
    boolean saveCoreStation(StationSaveDTO stationSaveDTO);

    /**
     * 修改岗位
     *
     * @param stationUpdateDTO 岗位更新对象
     * @return boolean
     */
    boolean updateCoreStation(StationUpdateDTO stationUpdateDTO);

    /**
     * 根据名称查询岗位信息
     * @param stationName 岗位名称
     * @return CoreStation
     */
    CoreStation getCoreStationByName(String stationName);
}
