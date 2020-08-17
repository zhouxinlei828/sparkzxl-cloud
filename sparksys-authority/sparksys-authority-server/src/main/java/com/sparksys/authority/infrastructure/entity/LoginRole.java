package com.sparksys.authority.infrastructure.entity;

import lombok.Data;

import java.util.List;

/**
 * description: 登录角色
 *
 * @author: zhouxinlei
 * @date: 2020-08-17 11:39:25
 */
@Data
public class LoginRole {

    private Long id;
    private String name;
    private String describe;
    private List<LoginPermission> permissions;

}
