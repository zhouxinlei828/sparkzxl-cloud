package com.github.sparkzxl.authorization.interfaces.dto.user;

import com.github.sparkzxl.database.entity.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * description: 用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:24:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AuthUserStatusDTO状态修改对象", description = "AuthUserStatusDTO状态修改对象")
public class AuthUserStatusDTO {

    @ApiModelProperty(value = "id")
    @NotEmpty(message = "用户ID不能为空")
    private Long id;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    private Boolean status;

    @ApiModelProperty(value = "职位状态")
    private RemoteData<String, String> positionStatus;

    @ApiModelProperty(value = "更新人id")
    private Long updateUser;

}
