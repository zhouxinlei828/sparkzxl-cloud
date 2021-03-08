package com.github.sparkzxl.authorization.interfaces.dto.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 资源分页对象
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:24:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "资源分页对象", description = "资源分页对象")
public class AuthResourcePageDTO {

    private static final long serialVersionUID = -6295580114270886981L;

    @ApiModelProperty(value = "编码规则： 链接： 数据列： 按钮：")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;
}
