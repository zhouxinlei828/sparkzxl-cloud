package com.github.sparkzxl.authority.infrastructure.convert;

import com.github.sparkzxl.authority.infrastructure.entity.AuthResource;
import com.github.sparkzxl.authority.interfaces.dto.resource.AuthResourceDTO;
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

    /**
     * AuthResource转化为 AuthResourceDTO
     *
     * @param authResource 资源
     * @return AuthResourceDTO
     */
    AuthResourceDTO convertAuthResourceDTO(AuthResource authResource);

}
