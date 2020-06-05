package com.sparksys.authorization.interfaces.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * description: 登录入参
 *
 * @Author zhouxinlei
 * @Date 2020-06-05 21:46:40
 */
@Data
@Accessors(chain = true)
public class AuthUserLoginDTO {


    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

}
