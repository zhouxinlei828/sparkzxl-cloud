package com.sparksys.oauth.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/7 3:28 下午
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    MAN(1, "男"),
    WOMAN(2, "女"),
    NONE(0, "未知");

    private int code;

    private String value;

}
