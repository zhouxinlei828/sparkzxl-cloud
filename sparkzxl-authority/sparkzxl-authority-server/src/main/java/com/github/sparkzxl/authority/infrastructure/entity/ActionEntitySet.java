package com.github.sparkzxl.authority.infrastructure.entity;

import lombok.Data;

/**
 * description: 动作属性
 *
 * @author: zhouxinlei
 * @date: 2020-08-17 13:37:06
 */
@Data
public class ActionEntitySet {

    private String action;
    private String describe;
    private boolean defaultCheck;

}
