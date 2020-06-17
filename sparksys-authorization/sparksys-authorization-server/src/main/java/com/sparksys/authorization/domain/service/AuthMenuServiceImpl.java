package com.sparksys.authorization.domain.service;

import com.sparksys.authorization.application.service.IAuthMenuService;
import com.sparksys.authorization.domain.repository.IAuthMenuRepository;
import com.sparksys.authorization.infrastructure.entity.AuthMenu;
import com.sparksys.commons.mybatis.utils.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 菜单 服务实现类
 *
 * @Author zhouxinlei
 * @Date 2020-06-07 13:35:18
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
