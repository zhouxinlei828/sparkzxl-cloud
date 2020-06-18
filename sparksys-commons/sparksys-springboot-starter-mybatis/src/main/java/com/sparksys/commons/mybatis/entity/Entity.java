package com.sparksys.commons.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/17 0017
 */
public class Entity<T> extends SuperEntity<T> {

    public static final String UPDATE_TIME = "updateTime";
    public static final String UPDATE_USER = "updateUser";
    private static final long serialVersionUID = 5169873634279173683L;
    @ApiModelProperty("最后修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;
    @ApiModelProperty("最后修改人ID")
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    protected T updateUser;

    public Entity(T id, LocalDateTime createTime, T createUser, LocalDateTime updateTime, T updateUser) {
        super(id, createTime, createUser);
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }

    public Entity() {
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public T getUpdateUser() {
        return this.updateUser;
    }

    public Entity<T> setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Entity<T> setUpdateUser(T updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public String toString() {
        return "Entity(super=" + super.toString() + ", updateTime=" + this.getUpdateTime() + ", updateUser=" + this.getUpdateUser() + ")";
    }

}
