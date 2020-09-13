
package com.github.sparkzxl.authority.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * description: 省份
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 14:51:48
 */
@Data
public class Province {

    @JsonProperty("key")
    private Long id;

    @JsonProperty("label")
    private String name;

}
