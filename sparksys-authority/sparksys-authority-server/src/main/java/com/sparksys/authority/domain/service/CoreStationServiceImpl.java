package com.sparksys.authority.domain.service;

import com.github.pagehelper.PageInfo;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authority.application.service.ICoreStationService;
import com.sparksys.authority.domain.repository.ICoreStationRepository;
import com.sparksys.authority.infrastructure.constant.CacheConstant;
import com.sparksys.authority.infrastructure.convert.CoreStationConvert;
import com.sparksys.authority.infrastructure.entity.CoreStation;
import com.sparksys.authority.infrastructure.mapper.CoreStationMapper;
import com.sparksys.authority.interfaces.dto.station.StationPageDTO;
import com.sparksys.authority.interfaces.dto.station.StationSaveDTO;
import com.sparksys.authority.interfaces.dto.station.StationUpdateDTO;
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
