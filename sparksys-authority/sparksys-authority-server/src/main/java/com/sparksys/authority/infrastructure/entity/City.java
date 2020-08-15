
package com.sparksys.authority.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * description: 城市
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 14:41:41
*/
@Data
public class City {

    @JsonProperty("key")
    private Long id;

    @JsonProperty("label")
    private String name;

}
