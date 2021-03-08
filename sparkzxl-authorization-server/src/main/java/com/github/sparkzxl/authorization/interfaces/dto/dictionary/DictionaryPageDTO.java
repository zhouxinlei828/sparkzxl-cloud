package com.github.sparkzxl.authorization.interfaces.dto.dictionary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: 字典查询入参
 *
 * @author: zhouxinlei
 * @date: 2020-12-02 10:13:57
*/
@Data
@ApiModel("字典查询入参")
public class DictionaryPageDTO {

    @ApiModelProperty(value = "编码一颗树仅仅有一个统一的编码")
    private String type;

    @ApiModelProperty(value = "名称")
    private String name;
}
