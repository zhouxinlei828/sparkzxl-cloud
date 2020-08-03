package com.sparksys.authorization.infrastructure.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sparksys.core.enums.Enumerator;
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
public enum SexEnum implements Enumerator {

    /**
     * 性别枚举
     */
    MAN(1, "男"),
    WOMAN(2, "女"),
    NONE(0, "未知");

    @EnumValue
    @JsonValue
    final Integer code;

    final String desc;

}
