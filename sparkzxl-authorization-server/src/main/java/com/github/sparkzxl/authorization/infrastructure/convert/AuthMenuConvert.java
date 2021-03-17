package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.AuthMenu;
import com.github.sparkzxl.authorization.interfaces.dto.menu.AuthMenuSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthMenu 对象Convert
 *
 * @author charles.zhou
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthMenuConvert {

    AuthMenuConvert INSTANCE = Mappers.getMapper(AuthMenuConvert.class);

    /**
     * authMenuSaveDTO转换为AuthMenu
     *
     * @param authMenuSaveDTO 菜单保存对象
     * @return AuthMenu
     */
    AuthMenu convertAuthMenu(AuthMenuSaveDTO authMenuSaveDTO);
}
