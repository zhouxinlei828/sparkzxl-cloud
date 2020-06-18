package com.sparksys.authorization.infrastructure.convert;

import com.sparksys.authorization.infrastructure.entity.AuthRole;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.authorization.interfaces.dto.role.AuthRoleUpdateDTO;
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

    AuthRole convertAuthRoleDO(AuthRoleSaveDTO authRoleSaveDTO);

    AuthRole convertAuthRoleDO(AuthRoleUpdateDTO authRoleUpdateDTO);

    AuthRoleDTO convertAuthUserDTO(AuthRole authRole);

}
