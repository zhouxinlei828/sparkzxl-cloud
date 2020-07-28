package com.sparksys.oauth.interfaces.dto.station;

import com.sparksys.database.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: 岗位分页查询对象
 *
 * @author: zhouxinlei
 * @date: 2020-07-27 19:49:46
 */
@Data
@ApiModel(value = "岗位分页查询对象")
public class StationPageDTO extends PageDTO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

}
