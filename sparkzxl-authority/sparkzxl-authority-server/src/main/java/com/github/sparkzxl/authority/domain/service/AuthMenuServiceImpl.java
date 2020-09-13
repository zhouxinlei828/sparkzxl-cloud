package com.github.sparkzxl.authority.domain.service;

import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.database.utils.TreeUtil;
import com.github.sparkzxl.authority.application.service.IAuthMenuService;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authority.infrastructure.entity.AuthMenu;
import com.github.sparkzxl.authority.infrastructure.mapper.AuthMenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 菜单 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:35:18
 */
@Service
public class AuthMenuServiceImpl extends AbstractSuperCacheServiceImpl<AuthMenuMapper, AuthMenu> implements IAuthMenuService {
    @Override
    public List<AuthMenu> findMenuTree() {
        List<AuthMenu> authMenuList = list();
        return TreeUtil.buildTree(authMenuList);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.MENU;
    }
}
