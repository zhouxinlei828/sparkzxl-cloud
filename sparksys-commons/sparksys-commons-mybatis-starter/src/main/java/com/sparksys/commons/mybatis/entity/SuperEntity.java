package com.sparksys.commons.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-06-17 20:14:48
*/
public class SuperEntity<T> implements Serializable {

    private static final long serialVersionUID = -4603650115461757622L;

    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty("主键")
    protected T id;
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;
    @ApiModelProperty("创建人ID")
    @TableField(value = "create_user")
    protected T createUser;

    public T getId() {
        return this.id;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public T getCreateUser() {
        return this.createUser;
    }

    public SuperEntity<T> setId(T id) {
        this.id = id;
        return this;
    }

    public SuperEntity<T> setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public SuperEntity<T> setCreateUser(T createUser) {
        this.createUser = createUser;
        return this;
    }

    public SuperEntity() {
    }

    public SuperEntity(T id, LocalDateTime createTime, T createUser) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
    }

    public String toString() {
        return "SuperEntity(super=" + super.toString() + ", id=" + this.getId() + ", createTime=" + this.getCreateTime() + ", createUser=" + this.getCreateUser() + ")";
    }
}
