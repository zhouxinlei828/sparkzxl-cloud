package com.sparksys.oauth.domain.repository;


import com.sparksys.oauth.infrastructure.entity.AuthMenu;

import java.util.List;

/**
 * description: 菜单 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:12
 */
public interface IAuthMenuRepository {

    List<AuthMenu> selectList();

}
