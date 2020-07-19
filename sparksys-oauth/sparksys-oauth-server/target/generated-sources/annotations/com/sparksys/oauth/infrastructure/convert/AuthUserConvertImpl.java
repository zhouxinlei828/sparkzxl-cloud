package com.sparksys.oauth.infrastructure.convert;

import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.database.model.RemoteData;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.entity.CoreOrg;
import com.sparksys.oauth.infrastructure.entity.CoreStation;
import com.sparksys.oauth.infrastructure.entity.UserInfo;
import com.sparksys.oauth.infrastructure.enums.SexEnum;
import com.sparksys.oauth.interfaces.dto.user.AuthUserDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserUpdateDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-19T21:36:59+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class AuthUserConvertImpl implements AuthUserConvert {

    @Override
    public AuthUser convertAuthUserDO(AuthUserSaveDTO authUserSaveDTO) {
        if ( authUserSaveDTO == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setAccount( authUserSaveDTO.getAccount() );
        authUser.setName( authUserSaveDTO.getName() );
        authUser.setEmail( authUserSaveDTO.getEmail() );
        authUser.setMobile( authUserSaveDTO.getMobile() );
        if ( authUserSaveDTO.getSex() != null ) {
            authUser.setSex( Enum.valueOf( SexEnum.class, authUserSaveDTO.getSex() ) );
        }
        authUser.setAvatar( authUserSaveDTO.getAvatar() );
        authUser.setNation( authUserSaveDTO.getNation() );
        authUser.setEducation( authUserSaveDTO.getEducation() );
        authUser.setPositionStatus( authUserSaveDTO.getPositionStatus() );
        authUser.setWorkDescribe( authUserSaveDTO.getWorkDescribe() );
        authUser.setPassword( authUserSaveDTO.getPassword() );

        return authUser;
    }

    @Override
    public AuthUser convertAuthUserDO(AuthUserUpdateDTO authUserUpdateDTO) {
        if ( authUserUpdateDTO == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setId( authUserUpdateDTO.getId() );
        authUser.setName( authUserUpdateDTO.getName() );
        authUser.setEmail( authUserUpdateDTO.getEmail() );
        authUser.setMobile( authUserUpdateDTO.getMobile() );
        if ( authUserUpdateDTO.getSex() != null ) {
            authUser.setSex( Enum.valueOf( SexEnum.class, authUserUpdateDTO.getSex() ) );
        }
        authUser.setAvatar( authUserUpdateDTO.getAvatar() );
        authUser.setNation( authUserUpdateDTO.getNation() );
        authUser.setEducation( authUserUpdateDTO.getEducation() );
        authUser.setWorkDescribe( authUserUpdateDTO.getWorkDescribe() );

        return authUser;
    }

    @Override
    public AuthUser convertAuthUserDO(AuthUserStatusDTO authUserStatusDTO) {
        if ( authUserStatusDTO == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setUpdateUser( authUserStatusDTO.getUpdateUser() );
        authUser.setId( authUserStatusDTO.getId() );
        authUser.setStatus( authUserStatusDTO.getStatus() );
        authUser.setPositionStatus( authUserStatusDTO.getPositionStatus() );

        return authUser;
    }

    @Override
    public AuthUserInfo convertAuthUserInfo(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserInfo authUserInfo = new AuthUserInfo();

        authUserInfo.setId( authUser.getId() );
        authUserInfo.setAccount( authUser.getAccount() );
        authUserInfo.setPassword( authUser.getPassword() );
        authUserInfo.setName( authUser.getName() );
        authUserInfo.setStatus( authUser.getStatus() );

        return authUserInfo;
    }

    @Override
    public AuthUserDTO convertAuthUserDTO(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserDTO authUserDTO = new AuthUserDTO();

        authUserDTO.setSex( authUser.getSex() );
        authUserDTO.setId( authUser.getId() );
        authUserDTO.setAccount( authUser.getAccount() );
        authUserDTO.setName( authUser.getName() );
        authUserDTO.setOrg( authUser.getOrg() );
        authUserDTO.setStation( authUser.getStation() );
        authUserDTO.setEmail( authUser.getEmail() );
        authUserDTO.setMobile( authUser.getMobile() );
        authUserDTO.setStatus( authUser.getStatus() );
        authUserDTO.setAvatar( authUser.getAvatar() );
        authUserDTO.setNation( authUser.getNation() );
        authUserDTO.setEducation( authUser.getEducation() );
        authUserDTO.setPositionStatus( authUser.getPositionStatus() );
        authUserDTO.setWorkDescribe( authUser.getWorkDescribe() );
        authUserDTO.setLastLoginTime( authUser.getLastLoginTime() );
        authUserDTO.setCreateUser( authUser.getCreateUser() );
        authUserDTO.setCreateTime( authUser.getCreateTime() );
        authUserDTO.setUpdateUser( authUser.getUpdateUser() );
        authUserDTO.setUpdateTime( authUser.getUpdateTime() );

        return authUserDTO;
    }

    @Override
    public UserInfo converUserInfo(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setStation( authUserStationData( authUser ) );
        userInfo.setOrg( authUserOrgData( authUser ) );
        userInfo.setSignature( authUser.getWorkDescribe() );
        userInfo.setPhone( authUser.getMobile() );
        userInfo.setUserId( authUser.getId() );
        userInfo.setSex( authUser.getSex() );
        userInfo.setAccount( authUser.getAccount() );
        userInfo.setName( authUser.getName() );
        userInfo.setEmail( authUser.getEmail() );
        userInfo.setStatus( authUser.getStatus() );
        userInfo.setAvatar( authUser.getAvatar() );
        userInfo.setNation( authUser.getNation() );
        userInfo.setEducation( authUser.getEducation() );
        userInfo.setPositionStatus( authUser.getPositionStatus() );
        userInfo.setLastLoginTime( authUser.getLastLoginTime() );

        return userInfo;
    }

    private CoreStation authUserStationData(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }
        RemoteData<Long, CoreStation> station = authUser.getStation();
        if ( station == null ) {
            return null;
        }
        CoreStation data = station.getData();
        if ( data == null ) {
            return null;
        }
        return data;
    }

    private CoreOrg authUserOrgData(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }
        RemoteData<Long, CoreOrg> org = authUser.getOrg();
        if ( org == null ) {
            return null;
        }
        CoreOrg data = org.getData();
        if ( data == null ) {
            return null;
        }
        return data;
    }
}
