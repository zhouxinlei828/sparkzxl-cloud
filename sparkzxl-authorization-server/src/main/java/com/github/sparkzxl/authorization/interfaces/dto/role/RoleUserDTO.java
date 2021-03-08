package com.github.sparkzxl.authorization.interfaces.dto.role;

import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/16 0016
 */
@Data
public class RoleUserDTO {

    @NotNull(message = "角色id")
    private Long id;

    @NotNull(message = "用户列表")
    private List<AuthUserDTO> authUsers;

}
