package com.sparksys.oauth.infrastructure.convert;

import com.sparksys.commons.core.entity.GlobalAuthUser;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.interfaces.dto.user.AuthUserDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserUpdateDTO;
import org.mapstruct.Mapper;
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

    AuthUser convertAuthUserDO(AuthUserSaveDTO authUserSaveDTO);

    AuthUser convertAuthUserDO(AuthUserUpdateDTO authUserUpdateDTO);

    AuthUser convertAuthUserDO(AuthUserStatusDTO authUserStatusDTO);

    GlobalAuthUser convertGlobalAuthUser(AuthUser authUser);

    AuthUserDTO convertAuthUserDTO(AuthUser authUser);

}
