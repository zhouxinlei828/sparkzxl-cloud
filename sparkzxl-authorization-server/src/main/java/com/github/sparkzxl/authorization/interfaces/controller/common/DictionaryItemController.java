package com.github.sparkzxl.authorization.interfaces.controller.common;


import com.github.sparkzxl.authorization.application.service.ICommonDictionaryItemService;
import com.github.sparkzxl.authorization.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.authorization.interfaces.dto.dictionary.DictionaryItemQueryDTO;
import com.github.sparkzxl.authorization.interfaces.dto.dictionary.DictionaryItemSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.dictionary.DictionaryItemUpdateDTO;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.PageParams;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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
        CommonDictionaryItem, DictionaryItemSaveDTO, DictionaryItemUpdateDTO, DictionaryItemQueryDTO, Object> {

    @Override
    public List<CommonDictionaryItem> query(DictionaryItemQueryDTO dictionaryItemQueryDTO) {
        return baseService.findDictionaryItemList(dictionaryItemQueryDTO);
    }

    @Override
    public void handlerQueryParams(PageParams<DictionaryItemQueryDTO> params) {
        DictionaryItemQueryDTO paramsModel = params.getModel();
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
