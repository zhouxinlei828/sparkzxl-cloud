package com.github.sparkzxl.authorization.domain.service;

import cn.hutool.core.convert.Convert;
import com.github.sparkzxl.authorization.application.service.IAuthMenuService;
import com.github.sparkzxl.authorization.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.authorization.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authorization.infrastructure.convert.AuthMenuConvert;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthMenu;
import com.github.sparkzxl.authorization.infrastructure.mapper.AuthMenuMapper;
import com.github.sparkzxl.authorization.infrastructure.repository.AuthMenuRepository;
import com.github.sparkzxl.authorization.interfaces.dto.menu.AuthMenuSaveDTO;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.database.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthMenuRepository authMenuRepository;

    @Override
    public List<AuthMenu> findMenuTree(String label) {
        return TreeUtil.buildTree(authMenuRepository.findMenuTree(label));
    }

    @Override
    public boolean deleteMenu(List<Long> ids) {
        return authMenuRepository.deleteMenu(ids);
    }

    @Override
    public List<MenuBasicInfo> routers() {
        Long userId = BaseContextHandler.getUserId(Long.TYPE);
        return authMenuRepository.getAuthMenuList(userId);
    }

    @Override
    public boolean saveMenu(AuthMenuSaveDTO authMenuSaveDTO) {
        authMenuSaveDTO.setIsEnable(Convert.toBool(authMenuSaveDTO.getIsEnable(), true));
        authMenuSaveDTO.setHidden(Convert.toBool(authMenuSaveDTO.getHidden(), true));
        authMenuSaveDTO.setNoKeepAlive(Convert.toBool(authMenuSaveDTO.getNoKeepAlive(), true));
        authMenuSaveDTO.setParentId(Convert.toLong(authMenuSaveDTO.getParentId(), 0L));
        AuthMenu authMenu = AuthMenuConvert.INSTANCE.convertAuthMenu(authMenuSaveDTO);
        return authMenuRepository.saveMenu(authMenu);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.MENU;
    }
}
