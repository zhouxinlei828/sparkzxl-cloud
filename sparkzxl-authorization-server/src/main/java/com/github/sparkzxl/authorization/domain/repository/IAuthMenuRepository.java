package com.github.sparkzxl.authorization.domain.repository;


import com.github.sparkzxl.authorization.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthMenu;

import java.util.List;

/**
 * description: 菜单 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:12
 */
public interface IAuthMenuRepository {

    /**
     * 获取菜单列表
     *
     * @param userId 用户id
     * @return List<MenuBasicInfo>
     */
    List<MenuBasicInfo> getAuthMenuList(Long userId);

    /**
     * 保存菜单信息
     *
     * @param authMenus  菜单集合
     * @param tenantCode 租户code
     */
    void saveAuthMenus(List<AuthMenu> authMenus, String tenantCode);

    /**
     * 查询菜单列表
     *
     * @return List<AuthMenu>
     */
    List<AuthMenu> findAuthMenuList();

    /**
     * 根据租户code删除菜单
     *
     * @param tenantCode 租户code
     */
    void deleteTenantMenu(String tenantCode);

    /**
     * 保存菜单信息
     *
     * @param authMenu 菜单
     * @return boolean
     */
    boolean saveMenu(AuthMenu authMenu);

    /**
     * 删除菜单
     *
     * @param ids 菜单id列表
     * @return boolean
     */
    boolean deleteMenu(List<Long> ids);
}
