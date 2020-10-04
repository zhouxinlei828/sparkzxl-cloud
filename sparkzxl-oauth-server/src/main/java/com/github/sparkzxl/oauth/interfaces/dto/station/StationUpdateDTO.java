package com.github.sparkzxl.oauth.interfaces.dto.station;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * description: 岗位更新对象
 *
 * @author: zhouxinlei
 * @date: 2020-07-27 19:49:46
 */
@Data
@ApiModel(value = "岗位更新对象")
public class StationUpdateDTO {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "岗位id不能为空")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "状态")
    private Boolean status;

    @ApiModelProperty(value = "描述")
    @TableField("describe_")
    private String describe;

}
