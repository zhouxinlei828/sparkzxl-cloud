package com.github.sparkzxl.authority.interfaces.controller.common;


import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.authority.application.service.ICommonAreaService;
import com.github.sparkzxl.authority.infrastructure.entity.CommonArea;
import com.github.sparkzxl.authority.interfaces.dto.area.AreaPageDTO;
import com.github.sparkzxl.authority.interfaces.dto.area.AreaQueryDTO;
import com.github.sparkzxl.authority.interfaces.dto.area.AreaSaveDTO;
import com.github.sparkzxl.authority.interfaces.dto.area.AreaUpdateDTO;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * description: 地区管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:48:13
 */
@RestController
@ResponseResult
@Api(tags = "地区管理")
@RequestMapping("/common/area")
public class AreaController extends SuperCacheController<ICommonAreaService, Long,
        CommonArea, AreaPageDTO, AreaSaveDTO, AreaUpdateDTO> {


    @ApiOperation("查询地区列表")
    @GetMapping("/tree")
    public List<CommonArea> getAreaList(AreaQueryDTO areaQueryDTO) {
        return super.baseService.getAreaList(areaQueryDTO);
    }

    @ApiOperation("导入城市地区信息")
    @PostMapping("/importAreaJsonData")
    public boolean importAreaJsonData(@RequestParam("jsonFile") MultipartFile multipartFile) {
        return super.baseService.importAreaJsonData(multipartFile);
    }

}
