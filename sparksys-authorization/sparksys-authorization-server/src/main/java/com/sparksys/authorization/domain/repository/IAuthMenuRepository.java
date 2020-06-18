package com.sparksys.authorization.domain.repository;


import com.sparksys.authorization.infrastructure.entity.AuthMenu;

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
