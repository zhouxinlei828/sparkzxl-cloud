package com.sparksys.authority.interfaces.dto.role;

import com.sparksys.database.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/16 0016
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色分页查询对象")
public class AuthRolePageDTO extends PageDTO {

    @ApiModelProperty(value = "角色名称")
    private String name;

}
