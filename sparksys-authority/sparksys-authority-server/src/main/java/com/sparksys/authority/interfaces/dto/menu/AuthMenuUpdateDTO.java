package com.sparksys.authority.interfaces.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: 菜单更新对象
 *
 * @author: zhouxinlei
 * @date: 2020-08-15 18:05:09
 */
@Data
@ApiModel(value = "菜单更新对象")
public class AuthMenuUpdateDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String label;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "公共菜单 True是无需分配所有人就可以访问的")
    private Boolean isPublic;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "状态")
    private Boolean isEnable;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "分组")
    private String group;

    @ApiModelProperty(value = "描述", example = "0")
    protected Long parentId;

    @ApiModelProperty(value = "排序值", example = "1")
    protected Integer sortValue;

}
