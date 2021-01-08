package com.github.sparkzxl.activiti.infrastructure.entity;

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
 * description: 流程用户信息
 *
 * @author: zhouxinlei
 * @date: 2021-01-08 16:47:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ext_process_user")
@ApiModel(value = "ExtProcessUser对象", description = "")
public class ExtProcessUser implements Serializable {

    private static final long serialVersionUID = -1820198557795317460L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "用户姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    @TableField("status")
    private Boolean status;


}
