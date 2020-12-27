package com.github.sparkzxl.oauth.domain.model.aggregates;

import com.github.sparkzxl.oauth.infrastructure.entity.LoginPermission;
import lombok.Data;

import java.util.List;

/**
 * description: 登录角色
 *
 * @author: zhouxinlei
 * @date: 2020-08-17 11:39:25
 */
@Data
public class RoleBasicInfo {

    private Long id;
    private String name;

}
