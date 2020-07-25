package com.sparksys.activiti.interfaces.dto.act;

import com.sparksys.database.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: 模型分页查询
 *
 * @author: zhouxinlei
 * @date: 2020-07-25 11:20:31
 */
@Data
@ApiModel(value = "模型分页查询")
public class ModelPageDTO extends PageDTO {

    @ApiModelProperty("流程名称")
    private String name;

    @ApiModelProperty("流程key")
    private String key;
}
