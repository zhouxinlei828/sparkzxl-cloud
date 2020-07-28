package com.sparksys.oauth.interfaces.controller;


import com.github.pagehelper.PageInfo;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.ICoreStationService;
import com.sparksys.oauth.infrastructure.entity.CoreStation;
import com.sparksys.oauth.interfaces.dto.station.StationPageDTO;
import com.sparksys.oauth.interfaces.dto.station.StationSaveDTO;
import com.sparksys.oauth.interfaces.dto.station.StationUpdateDTO;
import com.sparksys.web.annotation.ResponseResult;
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
    public boolean saveCoreStation(@ApiIgnore AuthUserInfo authUserInfo,
                                   @Validated @RequestBody StationSaveDTO stationSaveDTO) {
        return stationService.saveCoreStation(authUserInfo.getId(), stationSaveDTO);
    }

    @ApiOperation("修改岗位")
    @PutMapping("/station")
    public boolean updateCoreStation(@ApiIgnore AuthUserInfo authUserInfo,
                                     @Validated @RequestBody StationUpdateDTO stationUpdateDTO) {
        return stationService.updateCoreStation(authUserInfo.getId(), stationUpdateDTO);
    }

    @ApiOperation("删除岗位")
    @DeleteMapping("/station/{id}")
    public boolean updateCoreStation(@PathVariable("id") Long id) {
        return stationService.removeById(id);
    }

}
