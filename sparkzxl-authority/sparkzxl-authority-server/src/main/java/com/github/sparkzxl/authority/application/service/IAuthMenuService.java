package com.github.sparkzxl.authority.application.service;


import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.authority.infrastructure.entity.AuthMenu;

import java.util.List;

/**
 * description: 菜单 服务类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:12
 */
public interface IAuthMenuService extends SuperCacheService<AuthMenu> {

    /**
     * 查询菜单tree
     *
     * @return List<AuthMenu>
     */
    List<AuthMenu> findMenuTree();

}
