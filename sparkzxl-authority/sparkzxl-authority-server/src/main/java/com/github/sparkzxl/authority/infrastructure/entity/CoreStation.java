package com.github.sparkzxl.authority.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.sparkzxl.database.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: 岗位
 *
 * @author zhouxinlei
 * @date  2020-06-07 13:23:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_core_station")
@ApiModel(value="CCoreStationDO对象", description="岗位")
public class CoreStation extends Entity<Long> {

    private static final long serialVersionUID = -4924681990812046498L;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    private Long orgId;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "描述")
    @TableField("describe_")
    private String describe;

}
