package com.sparksys.oauth.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 角色分配 账号角色绑定
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:55:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("c_auth_user_role")
@ApiModel(value = "CAuthUserRole对象", description = "角色分配账号角色绑定")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 3730006313872866511L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;
}
