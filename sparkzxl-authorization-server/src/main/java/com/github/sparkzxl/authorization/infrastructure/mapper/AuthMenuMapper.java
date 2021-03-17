package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthMenu;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * description: 菜单 Mapper 接口
 *
 * @author charles.zhou
 * @date 2020-06-07 13:28:51
 */
@Repository
public interface AuthMenuMapper extends SuperMapper<AuthMenu> {

    /**
     * 根据租户code删除菜单
     *
     * @param tenantCode 租户code
     */
    @Delete("delete from auth_menu where tenant_code = #{tenantCode}")
    @InterceptorIgnore(tenantLine = "true")
    void deleteTenantMenu(String tenantCode);
}
