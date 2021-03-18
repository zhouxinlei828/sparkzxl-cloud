package com.github.sparkzxl.authorization.interfaces.dto.tenant;

import com.github.sparkzxl.database.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 领域池分页查询对象
 *
 * @author charles.zhou
 * @date   2020-07-27 19:49:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "领域池分页查询对象")
public class RealmPoolPageDTO extends PageDTO {

    @ApiModelProperty(value = "领域池编码")
    private String code;

    @ApiModelProperty(value = "领域池名称")
    private String name;

}
