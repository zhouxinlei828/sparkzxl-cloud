package com.github.sparkzxl.oauth.interfaces.controller.common;


import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.oauth.application.service.ICommonAreaService;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonArea;
import com.github.sparkzxl.oauth.interfaces.dto.area.AreaPageDTO;
import com.github.sparkzxl.oauth.interfaces.dto.area.AreaQueryDTO;
import com.github.sparkzxl.oauth.interfaces.dto.area.AreaSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.area.AreaUpdateDTO;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 地区管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:48:13
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "地区管理")
@RequestMapping("/common/area")
public class AreaController extends SuperCacheController<ICommonAreaService, Long,
        CommonArea, AreaPageDTO, AreaSaveDTO, AreaUpdateDTO> {


    @ApiOperation("查询地区列表")
    @GetMapping("/tree")
    public List<CommonArea> getAreaList(AreaQueryDTO areaQueryDTO) {
        return super.baseService.getAreaList(areaQueryDTO);
    }

}
