package com.sparksys.oauth.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sparksys.database.annonation.InjectionField;
import com.sparksys.database.entity.Entity;
import com.sparksys.database.model.RemoteData;
import com.sparksys.oauth.infrastructure.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static com.sparksys.oauth.infrastructure.constant.InjectionFieldConstants.*;

/**
 * description: 用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:24:03
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_auth_user")
@ApiModel(value = "AuthUser对象", description = "用户")
public class AuthUser extends Entity<Long> {

    private static final long serialVersionUID = -2722880054053904869L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "账号")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = CoreOrg.class)
    private RemoteData<Long,CoreOrg> org;


    @ApiModelProperty(value = "岗位ID")
    @TableField("station_id")
    @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD)
    private RemoteData<Long, CoreStation> station;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "手机")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private SexEnum sex;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "民族")
    @TableField("nation")
    private String nation;

    @ApiModelProperty(value = "学历")
    @TableField("education")
    private String education;

    @ApiModelProperty(value = "职位状态")
    @TableField("position_status")
    private String positionStatus;

    @ApiModelProperty(value = "工作描述比如：市长、管理员、局长等等   用于登陆展示")
    @TableField("work_describe")
    private String workDescribe;

    @ApiModelProperty(value = "最后一次输错密码时间")
    @TableField("password_error_last_time")
    private LocalDateTime passwordErrorLastTime;

    @ApiModelProperty(value = "密码错误次数")
    @TableField("password_error_num")
    private Integer passwordErrorNum;

    @ApiModelProperty(value = "密码过期时间")
    @TableField("password_expire_time")
    private LocalDateTime passwordExpireTime;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

}
