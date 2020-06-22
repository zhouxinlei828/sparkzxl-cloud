package com.sparksys.oauth.domain.service;

import com.sparksys.oauth.application.service.IAuthMenuService;
import com.sparksys.oauth.domain.repository.IAuthMenuRepository;
import com.sparksys.oauth.infrastructure.entity.AuthMenu;
import com.sparksys.commons.mybatis.utils.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 菜单 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:35:18
 */
@Service
public class AuthMenuServiceImpl implements IAuthMenuService {

    private final IAuthMenuRepository authMenuRepository;

    public AuthMenuServiceImpl(IAuthMenuRepository authMenuRepository) {
        this.authMenuRepository = authMenuRepository;
    }

    @Override
    public List<AuthMenu> findMenuTree() {
        List<AuthMenu> authMenuList = authMenuRepository.selectList();
        return TreeUtil.buildTree(authMenuList);
    }
}
