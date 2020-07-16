package com.sparksys.oauth.application.service;


import com.sparksys.database.service.SuperCacheService;
import com.sparksys.oauth.infrastructure.entity.AuthMenu;

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
