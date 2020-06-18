package com.sparksys.authorization.infrastructure.repository;


import com.sparksys.authorization.domain.repository.IAuthMenuRepository;
import com.sparksys.authorization.infrastructure.mapper.AuthMenuMapper;
import com.sparksys.authorization.infrastructure.entity.AuthMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 菜单 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:12
 */
@Repository
public class AuthMenuRepository implements IAuthMenuRepository {

    private final AuthMenuMapper authMenuMapper;

    public AuthMenuRepository(AuthMenuMapper authMenuMapper) {
        this.authMenuMapper = authMenuMapper;
    }

    @Override
    public List<AuthMenu> selectList() {
        return authMenuMapper.selectList(null);
    }
}
