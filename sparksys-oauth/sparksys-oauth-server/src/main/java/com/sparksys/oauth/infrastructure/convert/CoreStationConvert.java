package com.sparksys.oauth.infrastructure.convert;

import com.sparksys.oauth.infrastructure.entity.CoreStation;
import com.sparksys.oauth.interfaces.dto.station.StationSaveDTO;
import com.sparksys.oauth.interfaces.dto.station.StationUpdateDTO;
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

    CoreStation convertCoreStation(StationSaveDTO stationSaveDTO);

    CoreStation convertCoreStation(StationUpdateDTO stationUpdateDTO);


}
