package com.github.sparkzxl.authority.domain.bo;

import com.github.sparkzxl.authority.infrastructure.enums.SexEnum;
import lombok.Data;

/**
 * description: 用户BO对象
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 20:26:38
 */
@Data
public class AuthUserBO {

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 状态 1启用 0禁用
     */
    private Boolean status;

    public void setSex(SexEnum sexEnum) {
        this.sex = sexEnum.getCode();
    }


}
