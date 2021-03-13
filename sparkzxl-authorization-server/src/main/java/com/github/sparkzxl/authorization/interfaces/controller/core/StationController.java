package com.github.sparkzxl.authorization.interfaces.controller.core;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.ICoreStationService;
import com.github.sparkzxl.authorization.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationUpdateDTO;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ICoreStationService stationService;

    @Autowired
    public void setStationService(ICoreStationService stationService) {
        this.stationService = stationService;
    }

    @ApiOperation("查询岗位分页列表")
    @PostMapping("/page")
    public PageInfo<CoreStation> getStationPageList(@RequestBody StationPageDTO stationPageDTO) {
        return stationService.getStationPageList(stationPageDTO);
    }

    @ApiOperation("查询岗位列表")
    @GetMapping("/list")
    public List<CoreStation> getStationList() {
        return stationService.list();
    }

    @ApiOperation("新增岗位")
    @PostMapping("/save")
    public boolean saveCoreStation(@Validated @RequestBody StationSaveDTO stationSaveDTO) {
        return stationService.saveCoreStation(stationSaveDTO);
    }

    @ApiOperation("修改岗位")
    @PutMapping("/update")
    public boolean updateCoreStation(@Validated @RequestBody StationUpdateDTO stationUpdateDTO) {
        return stationService.updateCoreStation(stationUpdateDTO);
    }

    @ApiOperation("删除岗位")
    @DeleteMapping("/delete")
    public boolean deleteCoreStation(@RequestBody DeleteDTO<Long> deleteDTO) {
        return stationService.deleteCoreStation(deleteDTO.getIds());
    }

}
