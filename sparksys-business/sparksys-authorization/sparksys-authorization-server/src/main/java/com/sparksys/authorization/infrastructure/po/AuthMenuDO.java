package com.sparksys.authorization.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: 菜单
 *
 * @Author zhouxinlei
 * @Date 2020-06-07 13:24:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_auth_menu")
@ApiModel(value = "CAuthMenuDO对象", description = "菜单")
public class AuthMenuDO implements Serializable {

    private static final long serialVersionUID = 7737221207197581152L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "租户编码")
    @TableField("tenant_code")
    private String tenantCode;

    @ApiModelProperty(value = "名称")
    @TableField("label")
    private String label;

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

    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "分组")
    @TableField("group_")
    private String group;

    @ApiModelProperty(value = "父级菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "创建人id")
    @TableField("create_user")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人id")
    @TableField("update_user")
    private Long updateUser;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
