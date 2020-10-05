package com.github.sparkzxl.oauth.interfaces.controller.common;


import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.oauth.application.service.ICommonDictionaryItemService;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 字典项管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:48:30
 */
@AllArgsConstructor
@RestController
@ResponseResult
@WebLog
@Api(tags = "字典项管理")
@RequestMapping("/common")
public class DictionaryItemController {

    private final ICommonDictionaryItemService dictionaryItemService;

    @ApiOperation("根据字典类型查询字典数据")
    @GetMapping("/dictionaryItem")
    public List<CommonDictionaryItem> findDictionaryItemByDictionaryType(@RequestParam("dictionaryType") String dictionaryType) {
        return dictionaryItemService.findDictionaryItemByDictionaryType(dictionaryType);
    }

}
