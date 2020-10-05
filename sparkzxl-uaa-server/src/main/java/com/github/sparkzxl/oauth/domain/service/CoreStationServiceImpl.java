package com.github.sparkzxl.oauth.domain.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.oauth.application.service.ICoreStationService;
import com.github.sparkzxl.oauth.domain.repository.ICoreStationRepository;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.convert.CoreStationConvert;
import com.github.sparkzxl.oauth.infrastructure.entity.CoreStation;
import com.github.sparkzxl.oauth.infrastructure.mapper.CoreStationMapper;
import com.github.sparkzxl.oauth.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.oauth.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.station.StationUpdateDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 岗位 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:37:46
 */
@Service
public class CoreStationServiceImpl extends AbstractSuperCacheServiceImpl<CoreStationMapper, CoreStation> implements ICoreStationService {

    @Autowired
    private ICoreStationRepository coreStationRepository;

    @Override
    public PageInfo<CoreStation> getStationPageList(StationPageDTO stationPageDTO) {
        return coreStationRepository.getStationPageList(stationPageDTO.getPageNum(),
                stationPageDTO.getPageSize(),
                stationPageDTO.getName(),
                stationPageDTO.getOrgId());
    }

    @Override
    public boolean saveCoreStation(Long userId, StationSaveDTO stationSaveDTO) {
        CoreStation coreStation = CoreStationConvert.INSTANCE.convertCoreStation(stationSaveDTO);
        coreStation.setCreateUser(userId);
        coreStation.setUpdateUser(userId);
        return save(coreStation);
    }

    @Override
    public boolean updateCoreStation(Long userId, StationUpdateDTO stationUpdateDTO) {
        CoreStation coreStation = CoreStationConvert.INSTANCE.convertCoreStation(stationUpdateDTO);
        coreStation.setUpdateUser(userId);
        return updateById(coreStation);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.STATION;
    }
}
