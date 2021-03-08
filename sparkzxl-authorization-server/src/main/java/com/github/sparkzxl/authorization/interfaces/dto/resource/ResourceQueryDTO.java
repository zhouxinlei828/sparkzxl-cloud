package com.github.sparkzxl.authorization.interfaces.dto.resource;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 资源 查询DTO
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 23:14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "ResourceQueryDTO", description = "资源查询")
public class ResourceQueryDTO {

    /**
     * 父资源id， 用于查询按钮
     */
    @ApiModelProperty(value = "菜单id", notes = "指定菜单id")
    private Long menuId;
    /**
     * 登录人用户id
     */
    @ApiModelProperty(value = "指定用户id", notes = "指定用户id，前端不传则自动获取")
    private Long userId;

}
