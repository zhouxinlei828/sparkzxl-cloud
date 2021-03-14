package com.github.sparkzxl.authorization.interfaces.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * description: 菜单查询对象
 *
 * @author: zhouxinlei
 * @date: 2021-03-14 13:28:08
 */
@Data
@ApiModel(value = "菜单查询对象")
public class AuthMenuQueryDTO {

    @ApiModelProperty(value = "菜单名称")
    private String label;

}
