package com.github.sparkzxl.authorization.interfaces.dto.station;

import com.github.sparkzxl.authorization.infrastructure.entity.CoreOrg;
import com.github.sparkzxl.database.dto.PageDTO;
import com.github.sparkzxl.database.entity.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 岗位分页查询对象
 *
 * @author: zhouxinlei
 * @date: 2020-07-27 19:49:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "岗位分页查询对象")
public class StationPageDTO extends PageDTO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组织")
    private RemoteData<Long, CoreOrg> org;

}
