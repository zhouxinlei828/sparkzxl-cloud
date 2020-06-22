package com.sparksys.oauth.interfaces.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * description: 用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:24:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AuthUserSaveDTO保存对象", description = "AuthUserSaveDTO保存对象")
public class AuthUserSaveDTO {

    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    private String account;

    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "组织ID")
    @NotNull(message = "组织不能为空")
    private Long orgId;

    @ApiModelProperty(value = "岗位ID")
    @NotNull(message = "岗位不能为空")
    private Long stationId;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "性别")
    @NotEmpty(message = "性别不能为空")
    private String sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "职位状态")
    private String positionStatus;

    @ApiModelProperty(value = "工作描述比如：市长、管理员、局长等等   用于登陆展示")
    private String workDescribe;

}
