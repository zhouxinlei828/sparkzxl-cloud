package com.sparksys.authorization.infrastructure.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/*
 * description：登录日志
 *
 * @author zhouxinlei
 * @date  2020/6/17 0017
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "LoginLog", description = "登录日志统计")
public class LoginLogCount {


    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private LocalDate loginDate;

    /**
     * 浏览器名称
     */
    @ApiModelProperty(value = "浏览器名称")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String operatingSystem;


    @ApiModelProperty(value = "数量")
    private Long count;
}
