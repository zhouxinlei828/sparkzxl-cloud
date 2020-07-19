package com.sparksys.oauth.infrastructure.convert;

import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.oauth.interfaces.dto.resource.AuthResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthResource 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthResourceConvert {

    AuthResourceConvert INSTANCE = Mappers.getMapper(AuthResourceConvert.class);

    AuthResourceDTO convertAuthResourceDTO(AuthResource authResource);

}
