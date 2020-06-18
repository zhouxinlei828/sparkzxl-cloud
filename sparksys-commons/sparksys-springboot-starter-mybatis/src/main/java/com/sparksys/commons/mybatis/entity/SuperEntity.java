package com.sparksys.commons.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-06-17 20:14:48
*/
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SuperEntity<T> implements Serializable {

    private static final long serialVersionUID = -4603650115461757622L;

    public static final String FIELD_ID = "id";
    public static final String CREATE_TIME = "createTime";
    public static final String CREATE_TIME_COLUMN = "create_time";
    public static final String CREATE_USER = "createUser";
    public static final String CREATE_USER_COLUMN = "create_user";

    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty("主键")
    protected T id;
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;
    @ApiModelProperty("创建人ID")
    @TableField(value = "create_user")
    protected T createUser;
}
