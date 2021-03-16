package com.github.sparkzxl.authorization.interfaces.dto.org;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * description: 组织查询对象
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 10:21:46
 */
@Data
@ApiModel("组织查询对象")
public class OrgQueryDTO {

    @ApiModelProperty(value = "名称")
    @Length(max = 255, message = "名称长度不能超过255")
    private String label;

}