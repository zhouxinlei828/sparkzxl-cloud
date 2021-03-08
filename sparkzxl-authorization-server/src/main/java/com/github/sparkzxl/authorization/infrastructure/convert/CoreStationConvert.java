package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: CoreStation对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface CoreStationConvert {

    CoreStationConvert INSTANCE = Mappers.getMapper(CoreStationConvert.class);

    /**
     * StationSaveDTO 转换为CoreStation
     *
     * @param stationSaveDTO 岗位保存对象
     * @return CoreStation
     */
    CoreStation convertCoreStation(StationSaveDTO stationSaveDTO);

    /**
     * StationUpdateDTO转换为CoreStation
     *
     * @param stationUpdateDTO 岗位更新对象
     * @return CoreStation
     */
    CoreStation convertCoreStation(StationUpdateDTO stationUpdateDTO);


}
