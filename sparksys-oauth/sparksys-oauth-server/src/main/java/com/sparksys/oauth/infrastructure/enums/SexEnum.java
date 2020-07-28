package com.sparksys.oauth.infrastructure.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
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

    /**
     * 性别枚举
     */
    MAN(1, "男"),
    WOMAN(2, "女"),
    NONE(0, "未知");

    @EnumValue
    @JsonValue
    private int code;

    private String desc;

}
