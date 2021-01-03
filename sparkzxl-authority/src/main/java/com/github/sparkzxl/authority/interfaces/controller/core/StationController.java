package com.github.sparkzxl.authority.interfaces.controller.core;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authority.application.service.ICoreStationService;
import com.github.sparkzxl.authority.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authority.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.authority.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.authority.interfaces.dto.station.StationUpdateDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class StationController {

    private final ICoreStationService stationService;

    public StationController(ICoreStationService stationService) {
        this.stationService = stationService;
    }

    @ApiOperation("查询岗位分页列表")
    @PostMapping("/stations")
    public PageInfo<CoreStation> getStationPageList(@RequestBody StationPageDTO stationPageDTO) {
        return stationService.getStationPageList(stationPageDTO);
    }

    @ApiOperation("查询岗位列表")
    @GetMapping("/stationList")
    public List<CoreStation> getStationList() {
        return stationService.list();
    }

    @ApiOperation("新增岗位")
    @PostMapping("/station")
    public boolean saveCoreStation(@Validated @RequestBody StationSaveDTO stationSaveDTO) {
        return stationService.saveCoreStation(stationSaveDTO);
    }

    @ApiOperation("修改岗位")
    @PutMapping("/station")
    public boolean updateCoreStation(@Validated @RequestBody StationUpdateDTO stationUpdateDTO) {
        return stationService.updateCoreStation(stationUpdateDTO);
    }

    @ApiOperation("删除岗位")
    @DeleteMapping("/station")
    public boolean updateCoreStation(@RequestParam("id") Long id) {
        return stationService.removeById(id);
    }

}
