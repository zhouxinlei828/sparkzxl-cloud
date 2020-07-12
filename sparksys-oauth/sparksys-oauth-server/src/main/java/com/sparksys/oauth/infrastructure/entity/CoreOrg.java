package com.sparksys.oauth.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sparksys.commons.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: 组织
 *
 * @author zhouxinlei
 * @date  2020-06-07 13:24:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_core_org")
@ApiModel(value="CCoreOrgDO对象", description="组织")
public class CoreOrg extends Entity<Long> {

    private static final long serialVersionUID = -7652204940090864043L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "简称")
    @TableField("abbreviation")
    private String abbreviation;

    @ApiModelProperty(value = "父ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "树结构")
    @TableField("tree_path")
    private String treePath;

    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "描述")
    @TableField("describe_")
    private String describe;

}
