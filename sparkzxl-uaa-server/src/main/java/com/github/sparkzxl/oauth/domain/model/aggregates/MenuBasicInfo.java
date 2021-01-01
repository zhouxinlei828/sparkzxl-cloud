package com.github.sparkzxl.oauth.domain.model.aggregates;

import com.github.sparkzxl.core.tree.TreeNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * description: 菜单基本信息
 *
 * @author: zhouxinlei
 * @date: 2020-08-17 11:39:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuBasicInfo extends TreeNode<MenuBasicInfo,Long> implements Serializable {

    private static final long serialVersionUID = 1126107430728988729L;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

}
