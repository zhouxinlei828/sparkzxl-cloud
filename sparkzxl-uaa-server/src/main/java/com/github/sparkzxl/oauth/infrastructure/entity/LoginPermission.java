package com.github.sparkzxl.oauth.infrastructure.entity;

import lombok.Data;

import java.util.List;

/**
 * description: 登录权限
 *
 * @author: zhouxinlei
 * @date: 2020-08-17 11:40:34
 */
@Data
public class LoginPermission {

    private Long roleId;
    private String permissionId;
    private String permissionName;
    private String actions;
    private List<ActionEntitySet> actionEntitySet;

}
