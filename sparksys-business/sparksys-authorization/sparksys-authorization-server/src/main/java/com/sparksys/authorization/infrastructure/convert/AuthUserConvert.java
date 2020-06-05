package com.sparksys.authorization.infrastructure.convert;

import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.interfaces.dto.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserLoginDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthUser对象Convert
 *
 * @Author zhouxinlei
 * @Date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthUserConvert {

    AuthUserConvert INSTANCE = Mappers.getMapper(AuthUserConvert.class);

    AuthUserDO convertAuthUserDO(AuthUserLoginDTO authUserLoginDTO);

    AuthUserDTO convertAuthUserDTO(AuthUserDO authUserDO);

}
