package com.github.sparkzxl.oauth.interfaces.controller.core;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.oauth.application.service.ICoreStationService;
import com.github.sparkzxl.oauth.infrastructure.entity.CoreStation;
import com.github.sparkzxl.oauth.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.oauth.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.station.StationUpdateDTO;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
public class StationController {

    private final ICoreStationService stationService;

    public StationController(ICoreStationService stationService) {
        this.stationService = stationService;
    }

    @ApiOperation("查询岗位列表")
    @GetMapping("/stations")
    public PageInfo<CoreStation> getStationPageList(StationPageDTO stationPageDTO) {
        return stationService.getStationPageList(stationPageDTO);
    }

    @ApiOperation("新增岗位")
    @PostMapping("/station")
    public boolean saveCoreStation(@ApiIgnore AuthUserInfo<Long> authUserInfo,
                                   @Validated @RequestBody StationSaveDTO stationSaveDTO) {
        return stationService.saveCoreStation(authUserInfo.getId(), stationSaveDTO);
    }

    @ApiOperation("修改岗位")
    @PutMapping("/station")
    public boolean updateCoreStation(@ApiIgnore AuthUserInfo<Long> authUserInfo,
                                     @Validated @RequestBody StationUpdateDTO stationUpdateDTO) {
        return stationService.updateCoreStation(authUserInfo.getId(), stationUpdateDTO);
    }

    @ApiOperation("删除岗位")
    @DeleteMapping("/station/{id}")
    public boolean updateCoreStation(@PathVariable("id") Long id) {
        return stationService.removeById(id);
    }

}
