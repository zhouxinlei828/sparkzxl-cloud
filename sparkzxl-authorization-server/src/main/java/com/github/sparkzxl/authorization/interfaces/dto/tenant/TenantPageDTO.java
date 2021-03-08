package com.github.sparkzxl.authorization.interfaces.dto.tenant;

import com.github.sparkzxl.database.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 租户分页查询对象
 *
 * @author: zhouxinlei
 * @date: 2020-07-27 19:49:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "租户分页查询对象")
public class TenantPageDTO extends PageDTO {

    @ApiModelProperty(value = "租户编码")
    private String code;

    @ApiModelProperty(value = "租户名称")
    private String name;

}
