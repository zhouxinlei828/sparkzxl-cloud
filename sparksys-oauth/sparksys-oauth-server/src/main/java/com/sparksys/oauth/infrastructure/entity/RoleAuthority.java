package com.sparksys.oauth.infrastructure.entity;

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

/**
 * description: 角色的资源
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:56:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("c_auth_role_authority")
@ApiModel(value = "CAuthRoleAuthority对象", description = "角色的资源")
public class RoleAuthority implements Serializable {

    private static final long serialVersionUID = -6764899985506548603L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "资源id")
    @TableField("authority_id")
    private Long authorityId;

    @ApiModelProperty(value = "权限类型#AuthorizeType{MENU:菜单;RESOURCE:资源;}")
    @TableField("authority_type")
    private String authorityType;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private Long createUser;
}
