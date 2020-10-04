package com.github.sparkzxl.oauth.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.sparkzxl.database.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: 菜单
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:24:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_auth_menu")
@ApiModel(value = "CAuthMenuDO对象", description = "菜单")
public class AuthMenu extends TreeEntity<AuthMenu, Long> {

    private static final long serialVersionUID = -5664444970808472308L;

    @ApiModelProperty(value = "code")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "描述")
    @TableField("describe_")
    private String describe;

    @ApiModelProperty(value = "公共菜单 True是无需分配所有人就可以访问的")
    @TableField("is_public")
    private Boolean isPublic;

    @ApiModelProperty(value = "路径")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "状态")
    @TableField("is_enable")
    private Boolean isEnable;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "分组")
    @TableField("group_")
    private String group;

}
