package com.github.sparkzxl.authority.infrastructure.entity;

import lombok.Data;

import java.util.List;

/**
 * description: 路由菜单
 *
 * @author: zhouxinlei
 * @date: 2020-08-17 15:43:31
 */
@Data
public class Router {
    private String title;
    private String key;
    private String name;
    private String component;
    private String icon;
    private String redirect;
    private List<Router> children;
}
