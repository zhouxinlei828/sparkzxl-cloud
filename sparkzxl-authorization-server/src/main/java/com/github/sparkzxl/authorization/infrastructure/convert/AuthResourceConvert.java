package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.AuthResource;
import com.github.sparkzxl.authorization.interfaces.dto.resource.ResourceUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthResource 对象Convert
 *
 * @author charles.zhou
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthResourceConvert {

    AuthResourceConvert INSTANCE = Mappers.getMapper(AuthResourceConvert.class);
    /**
     * authResourceUpdateDTO 转化为 AuthResource
     *
     * @param authResourceUpdateDTO 资源更新DTO
     * @return AuthResourceDTO
     */
    AuthResource convertAuthResourceDTO(ResourceUpdateDTO authResourceUpdateDTO);

}
