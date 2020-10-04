package com.github.sparkzxl.oauth.infrastructure.convert;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.oauth.domain.bo.AuthUserBO;
import com.github.sparkzxl.oauth.infrastructure.entity.AuthUser;
import com.github.sparkzxl.oauth.infrastructure.entity.LoginAuthUser;
import com.github.sparkzxl.oauth.infrastructure.entity.UserInfo;
import com.github.sparkzxl.oauth.interfaces.dto.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthUser对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthUserConvert {

    AuthUserConvert INSTANCE = Mappers.getMapper(AuthUserConvert.class);

    /**
     * AuthUser转化为AuthUserInfo
     *
     * @param authUser
     * @return AuthUserInfo
     */
    AuthUserInfo<Long> convertAuthUserInfo(AuthUser authUser);

    /**
     * AuthUserSaveDTO转化为 AuthUser
     *
     * @param authUserSaveDTO AuthUserSaveDTO保存对象
     * @return AuthUser
     */
    AuthUser convertAuthUser(AuthUserSaveDTO authUserSaveDTO);

    /**
     * AuthUserUpdateDTO转化为AuthUser
     *
     * @param authUserUpdateDTO AuthUserUpdateDTO更新对象
     * @return AuthUser
     */
    AuthUser convertAuthUser(AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * AuthUserStatusDTO转化为AuthUser
     *
     * @param authUserStatusDTO AuthUserStatusDTO状态修改对象
     * @return AuthUser
     */
    AuthUser convertAuthUser(AuthUserStatusDTO authUserStatusDTO);

    /**
     * AuthUserPageDTO转化为AuthUserBO
     *
     * @param authUserPageDTO AuthUserDTO分页查询对象
     * @return AuthUserBO
     */
    AuthUserBO convertAuthUserBO(AuthUserPageDTO authUserPageDTO);


    /**
     * AuthUser转化为AuthUserDTO
     *
     * @param authUser
     * @return AuthUserDTO
     */
    AuthUserDTO convertAuthUserDTO(AuthUser authUser);

    /**
     * PageInfo<AuthUser> 转化为PageInfo<AuthUserDTO>
     *
     * @param authUserPageInfo 分页对象
     * @return PageInfo<AuthUserDTO>
     */
    PageInfo<AuthUserDTO> convertAuthUserDTO(PageInfo<AuthUser> authUserPageInfo);

    /**
     * AuthUser转化为UserInfo
     *
     * @param authUser 用户
     * @return UserInfo
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "org.data", target = "org"),
            @Mapping(source = "station.data", target = "station"),
            @Mapping(source = "workDescribe", target = "signature"),
            @Mapping(source = "mobile", target = "phone")
    })
    UserInfo convertUserInfo(AuthUser authUser);

    /**
     * AuthUser转化为LoginAuthUser
     *
     * @param authUser
     * @return LoginAuthUser
     */
    LoginAuthUser convertLoginAuthUser(AuthUser authUser);
}
