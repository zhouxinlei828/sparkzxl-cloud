package com.github.sparkzxl.oauth.interfaces.controller.common;


import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.oauth.application.service.ICommonDictionaryService;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonDictionary;
import com.github.sparkzxl.oauth.interfaces.dto.dictionary.DictionaryPageDTO;
import com.github.sparkzxl.oauth.interfaces.dto.dictionary.DictionarySaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.dictionary.DictionaryUpdateDTO;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 字典类型管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:48:00
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "字典类型管理")
@RequestMapping("/common/dictionary")
public class DictionaryController extends SuperCacheController<ICommonDictionaryService, Long,
        CommonDictionary, DictionaryPageDTO, DictionarySaveDTO, DictionaryUpdateDTO> {


}
