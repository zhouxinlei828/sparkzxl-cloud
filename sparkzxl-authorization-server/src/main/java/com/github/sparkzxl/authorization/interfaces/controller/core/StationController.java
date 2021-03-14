package com.github.sparkzxl.authorization.interfaces.controller.core;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.ICoreStationService;
import com.github.sparkzxl.authorization.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationQueryDTO;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationUpdateDTO;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.database.dto.PageParams;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


/**
 * description: 岗位 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:41:11
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "岗位管理")
@RequestMapping("/station")
public class StationController extends SuperCacheController<ICoreStationService, Long,
        CoreStation, StationSaveDTO, StationUpdateDTO, StationQueryDTO, Object> {

    @Override
    public PageInfo<CoreStation> page(PageParams<StationQueryDTO> params) {
        return baseService.getStationPageList(params);
    }

    @Override
    public boolean save(StationSaveDTO stationSaveDTO) {
        return baseService.saveCoreStation(stationSaveDTO);
    }

    @Override
    public boolean update(StationUpdateDTO stationUpdateDTO) {
        return baseService.updateCoreStation(stationUpdateDTO);
    }

    @Override
    public boolean delete(DeleteDTO<Long> deleteDTO) {
        return baseService.deleteCoreStation(deleteDTO.getIds());
    }

}
