package com.sparksys.commons.security.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * description: 登录请求参数
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:38:55
 */
@Data
@ApiModel(description = "登录请求参数")
public class AuthRequest {

    @ApiModelProperty(value = "账户")
    @NotEmpty(message = "账户不能为空")
    private String account;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

}
