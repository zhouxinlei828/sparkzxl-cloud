package com.github.sparkzxl.oauth.interfaces.controller.common;


import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.PageParams;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.oauth.application.service.ICommonDictionaryItemService;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.oauth.interfaces.dto.dictionary.*;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/common/dictionaryItem")
public class DictionaryItemController extends SuperCacheController<ICommonDictionaryItemService, Long,
        CommonDictionaryItem, DictionaryItemPageDTO, DictionaryItemSaveDTO, DictionaryItemUpdateDTO> {

    private final ICommonDictionaryItemService dictionaryItemService;

    @ApiOperation("查询字典项数据列表")
    @GetMapping("/dictionaryItemList")
    public List<CommonDictionaryItem> findDictionaryItemList(DictionaryItemQueryDTO dictionaryItemQueryDTO) {
        return dictionaryItemService.findDictionaryItemList(dictionaryItemQueryDTO);
    }

    @Override
    public void handlerQueryParams(PageParams<DictionaryItemPageDTO> params) {
        DictionaryItemPageDTO paramsModel = params.getModel();
        if (ObjectUtils.isNotEmpty(paramsModel.getDictionaryId()) && paramsModel.getDictionaryId() == 0L) {
            paramsModel.setDictionaryId(null);
        }
        if (StringUtils.isEmpty(paramsModel.getCode())) {
            paramsModel.setCode(null);
        }
        if (StringUtils.isEmpty(paramsModel.getName())) {
            paramsModel.setName(null);
        }
    }
}
