package com.sparksys.authorization.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sparksys.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 字典类型
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:38:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("c_common_dictionary")
@ApiModel(value = "CCommonDictionary对象", description = "字典类型")
public class CommonDictionary extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码一颗树仅仅有一个统一的编码")
    @TableField("type_")
    private String type;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "描述")
    @TableField("describe_")
    private String describe;

    @ApiModelProperty(value = "状态")
    @TableField("status_")
    private Boolean status;

}
