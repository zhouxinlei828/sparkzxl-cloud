package com.sparksys.authority.infrastructure.convert;

import com.sparksys.authority.infrastructure.entity.AuthRole;
import com.sparksys.authority.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.authority.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.authority.interfaces.dto.role.AuthRoleUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthRole对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthRoleConvert {

    AuthRoleConvert INSTANCE = Mappers.getMapper(AuthRoleConvert.class);

    /**
     * AuthRoleSaveDTO转化为AuthRole
     *
     * @param authRoleSaveDTO 角色保存对象
     * @return AuthRole
     */
    AuthRole convertAuthRoleDO(AuthRoleSaveDTO authRoleSaveDTO);

    /**
     * authRoleUpdateDTO转化为AuthRole
     *
     * @param authRoleUpdateDTO 角色更新对象
     * @return AuthRole
     */
    AuthRole convertAuthRoleDO(AuthRoleUpdateDTO authRoleUpdateDTO);

    /**
     * authRole转化为AuthRoleDTO
     *
     * @param authRole 角色
     * @return AuthRoleDTO
     */
    AuthRoleDTO convertAuthUserDTO(AuthRole authRole);

}
