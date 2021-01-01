package com.github.sparkzxl.oauth.domain.repository;


import com.github.sparkzxl.oauth.domain.model.aggregates.MenuBasicInfo;

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
}
