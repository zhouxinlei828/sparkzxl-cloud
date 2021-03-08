package com.github.sparkzxl.authorization.interfaces.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/16 0016
 */

@Data
@ApiModel("角色分页查询对象")
public class AuthRolePageDTO {

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;
}
