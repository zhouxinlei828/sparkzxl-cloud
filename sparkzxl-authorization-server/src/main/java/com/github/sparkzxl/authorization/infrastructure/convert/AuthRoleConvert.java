package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.domain.model.aggregates.RoleBasicInfo;
import com.github.sparkzxl.authorization.domain.model.aggregates.RoleResource;
import com.github.sparkzxl.authorization.domain.model.vo.RoleResourceVO;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthRole;
import com.github.sparkzxl.authorization.interfaces.dto.role.AuthRoleDTO;
import com.github.sparkzxl.authorization.interfaces.dto.role.AuthRoleSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.role.AuthRoleUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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


    /**
     * 角色资源领域对象转换显示层对象
     * @param roleResource 角色资源领域对象
     * @return RoleResourceVO
     */
    RoleResourceVO convertRoleResourceVO(RoleResource roleResource);

    List<RoleBasicInfo> convertRoleBasicInfo(List<AuthRole> roleList);
}
