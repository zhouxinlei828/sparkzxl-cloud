package com.github.sparkzxl.authority.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import com.github.sparkzxl.authority.application.service.ICoreStationService;
import com.github.sparkzxl.authority.domain.repository.ICoreStationRepository;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authority.infrastructure.convert.CoreStationConvert;
import com.github.sparkzxl.authority.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authority.infrastructure.mapper.CoreStationMapper;
import com.github.sparkzxl.authority.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.authority.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.authority.interfaces.dto.station.StationUpdateDTO;
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
        return PageInfoUtils.pageInfo(coreStationRepository.getStationPageList(stationPageDTO.getPageNum(),
                stationPageDTO.getPageSize(),stationPageDTO.getName(),
                stationPageDTO.getOrg()));
    }

    @Override
    public boolean saveCoreStation(StationSaveDTO stationSaveDTO) {
        CoreStation coreStation = CoreStationConvert.INSTANCE.convertCoreStation(stationSaveDTO);
        return save(coreStation);
    }

    @Override
    public boolean updateCoreStation(StationUpdateDTO stationUpdateDTO) {
        CoreStation coreStation = CoreStationConvert.INSTANCE.convertCoreStation(stationUpdateDTO);
        return updateById(coreStation);
    }

    @Override
    public CoreStation getCoreStationByName(String stationName) {
        LambdaQueryWrapper<CoreStation> stationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        stationLambdaQueryWrapper.eq(CoreStation::getName,stationName);
        stationLambdaQueryWrapper.eq(CoreStation::getStatus,true).last("limit 1");;
        return getOne(stationLambdaQueryWrapper);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.STATION;
    }
}
