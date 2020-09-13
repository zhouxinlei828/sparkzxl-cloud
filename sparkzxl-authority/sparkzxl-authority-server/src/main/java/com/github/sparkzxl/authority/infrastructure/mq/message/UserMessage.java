package com.github.sparkzxl.authority.infrastructure.mq.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/7 5:09 下午
 */
@Data
@ToString
public class UserMessage implements Serializable {

    private static final long serialVersionUID = -8895896197064940286L;


    public static final String TOPIC = "USER_TOPIC_01";

    /**
     * 编号
     */
    private Integer id;
}
