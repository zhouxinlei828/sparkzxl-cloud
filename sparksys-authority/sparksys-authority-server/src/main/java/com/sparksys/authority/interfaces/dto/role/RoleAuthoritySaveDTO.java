package com.sparksys.authority.interfaces.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * description: 角色资源保存对象
 *
 * @author: zhouxinlei
 * @date: 2020-07-30 20:45:20
 */
@Data
@ApiModel("角色资源保存对象")
public class RoleAuthoritySaveDTO {

    @NotNull(message = "角色不能为空")
    @ApiModelProperty("角色id")
    private Long roleId;

    @NotNull(message = "资源不能为空")
    @ApiModelProperty("资源id")
    private Set<Long> authorityList;

    @NotEmpty(message = "权限类型不能为空")
    @ApiModelProperty("权限类型")
    private String authorityType;

}
