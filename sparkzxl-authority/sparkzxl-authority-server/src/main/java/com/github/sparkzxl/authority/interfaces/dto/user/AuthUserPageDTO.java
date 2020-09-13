package com.github.sparkzxl.authority.interfaces.dto.user;

import com.github.sparkzxl.database.dto.PageDTO;
import com.github.sparkzxl.authority.infrastructure.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * description: 用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:24:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AuthUserDTO分页查询对象", description = "AuthUserDTO分页查询对象")
public class AuthUserPageDTO extends PageDTO {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    private Boolean status;

    @ApiModelProperty(value = "职位状态")
    private String positionStatus;

    public void setSex(SexEnum sex) {
        this.sex = sex.getDesc();
    }

}
