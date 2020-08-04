package com.sparksys.authorization.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.database.utils.TreeUtil;
import com.sparksys.authorization.application.service.IAuthMenuService;
import com.sparksys.authorization.infrastructure.constant.CacheConstant;
import com.sparksys.authorization.infrastructure.entity.AuthMenu;
import com.sparksys.authorization.infrastructure.mapper.AuthMenuMapper;
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
