package com.github.sparkzxl.oauth.infrastructure.convert;

import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.oauth.infrastructure.entity.AuthUser;
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

    /**
     * AuthUser转化为AuthUserInfo
     *
     * @param authUser
     * @return AuthUserInfo
     */
    AuthUserInfo convertAuthUserInfo(AuthUser authUser);
}
