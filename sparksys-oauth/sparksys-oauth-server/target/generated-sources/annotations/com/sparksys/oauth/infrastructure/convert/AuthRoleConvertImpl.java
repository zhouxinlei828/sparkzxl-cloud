package com.sparksys.oauth.infrastructure.convert;

import com.sparksys.oauth.infrastructure.entity.AuthRole;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleSaveDTO;
import com.sparksys.oauth.interfaces.dto.role.AuthRoleUpdateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-19T21:36:59+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class AuthRoleConvertImpl implements AuthRoleConvert {

    @Override
    public AuthRole convertAuthRoleDO(AuthRoleSaveDTO authRoleSaveDTO) {
        if ( authRoleSaveDTO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setName( authRoleSaveDTO.getName() );
        authRole.setCode( authRoleSaveDTO.getCode() );
        authRole.setDescribe( authRoleSaveDTO.getDescribe() );
        authRole.setStatus( authRoleSaveDTO.getStatus() );
        authRole.setReadonly( authRoleSaveDTO.getReadonly() );
        authRole.setDsType( authRoleSaveDTO.getDsType() );

        return authRole;
    }

    @Override
    public AuthRole convertAuthRoleDO(AuthRoleUpdateDTO authRoleUpdateDTO) {
        if ( authRoleUpdateDTO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authRoleUpdateDTO.getId() );
        authRole.setName( authRoleUpdateDTO.getName() );
        authRole.setCode( authRoleUpdateDTO.getCode() );
        authRole.setDescribe( authRoleUpdateDTO.getDescribe() );
        authRole.setStatus( authRoleUpdateDTO.getStatus() );
        authRole.setReadonly( authRoleUpdateDTO.getReadonly() );
        authRole.setDsType( authRoleUpdateDTO.getDsType() );

        return authRole;
    }

    @Override
    public AuthRoleDTO convertAuthUserDTO(AuthRole authRole) {
        if ( authRole == null ) {
            return null;
        }

        AuthRoleDTO authRoleDTO = new AuthRoleDTO();

        authRoleDTO.setId( authRole.getId() );
        authRoleDTO.setName( authRole.getName() );
        authRoleDTO.setCode( authRole.getCode() );
        authRoleDTO.setDescribe( authRole.getDescribe() );
        authRoleDTO.setStatus( authRole.getStatus() );
        authRoleDTO.setReadonly( authRole.getReadonly() );
        authRoleDTO.setDsType( authRole.getDsType() );
        authRoleDTO.setCreateUser( authRole.getCreateUser() );
        authRoleDTO.setCreateTime( authRole.getCreateTime() );
        authRoleDTO.setUpdateUser( authRole.getUpdateUser() );
        authRoleDTO.setUpdateTime( authRole.getUpdateTime() );

        return authRoleDTO;
    }
}
