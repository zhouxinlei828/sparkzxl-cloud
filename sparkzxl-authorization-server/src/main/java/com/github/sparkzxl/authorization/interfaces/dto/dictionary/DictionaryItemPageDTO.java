package com.github.sparkzxl.authorization.interfaces.dto.dictionary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: 字典项查询入参
 *
 * @author: zhouxinlei
 * @date: 2020-12-02 10:13:57
*/
@Data
@ApiModel("字典项查询入参")
public class DictionaryItemPageDTO {

    @ApiModelProperty(value = "类型ID")
    private Long dictionaryId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;
}
