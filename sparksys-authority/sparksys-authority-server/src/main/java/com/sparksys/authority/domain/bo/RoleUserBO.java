package com.sparksys.authority.domain.bo;

import com.sparksys.authority.infrastructure.entity.AuthUser;
import lombok.Data;

import java.util.List;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/16 0016
 */
@Data
public class RoleUserBO {

    private Long id;

    private List<AuthUser> authUsers;

}
