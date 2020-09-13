package com.github.sparkzxl.authority.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sparkzxl.authority.infrastructure.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * description: 用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:24:03
 */
@Data
@ApiModel(value = "UserInfo对象", description = "用户信息")
public class UserInfo {

    private static final long serialVersionUID = 1849014360372480614L;

    @ApiModelProperty(value = "userId")
    private Long userId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "组织")
    private CoreOrg org;

    @ApiModelProperty(value = "岗位")
    private CoreStation station;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    private Boolean status;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "职位状态")
    private String positionStatus;

    @ApiModelProperty(value = "工作描述比如：市长、管理员、局长等等   用于登陆展示")
    @JsonProperty(value = "signature")
    private String signature;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "消息通知")
    private Long notifyCount;

    @ApiModelProperty(value = "消息通知")
    private Long unreadCount;

    private String address = "西湖区工专路 77 号";

    private String country = "china";

    @ApiModelProperty(value = "省市")
    private Geographic geographic;

    private List<Map<String,Object>> tags;

    public void setSex(SexEnum sexEnum) {
        this.sex = sexEnum.getDesc();
    }
}
