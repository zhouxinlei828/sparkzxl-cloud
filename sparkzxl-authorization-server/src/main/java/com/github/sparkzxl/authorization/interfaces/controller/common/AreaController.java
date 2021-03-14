package com.github.sparkzxl.authorization.interfaces.controller.common;


import com.github.sparkzxl.authorization.application.service.ICommonAreaService;
import com.github.sparkzxl.authorization.infrastructure.entity.CommonArea;
import com.github.sparkzxl.authorization.interfaces.dto.area.AreaQueryDTO;
import com.github.sparkzxl.authorization.interfaces.dto.area.AreaSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.area.AreaUpdateDTO;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.core.annotation.ResponseResult;
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
        CommonArea, AreaSaveDTO, AreaUpdateDTO, AreaQueryDTO, Object> {


    @ApiOperation("查询地区列表")
    @GetMapping("/tree")
    public List<CommonArea> getAreaList(AreaQueryDTO areaQueryDTO) {
        return super.baseService.getAreaList(areaQueryDTO);
    }

    @ApiOperation("导入城市地区信息")
    @PostMapping("/importCity")
    public boolean importAreaJsonData(@RequestParam("jsonFile") MultipartFile multipartFile) {
        return super.baseService.importAreaJsonData(multipartFile);
    }

}
