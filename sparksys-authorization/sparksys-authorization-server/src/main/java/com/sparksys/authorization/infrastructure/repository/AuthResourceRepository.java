package com.sparksys.authorization.infrastructure.repository;


import com.sparksys.authorization.domain.repository.IAuthResourceRepository;
import com.sparksys.authorization.infrastructure.entity.AuthResource;
import com.sparksys.authorization.infrastructure.mapper.AuthResourceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 资源 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:28
 */
@Repository
public class AuthResourceRepository implements IAuthResourceRepository {

    private final AuthResourceMapper authResourceMapper;

    public AuthResourceRepository(AuthResourceMapper authResourceMapper) {
        this.authResourceMapper = authResourceMapper;
    }

    @Override
    public List<AuthResource> authResourceList() {
        return authResourceMapper.selectList(null);
    }

    @Override
    public List<AuthResource> findVisibleResource(Long userId, Long menuId) {
        return authResourceMapper.findVisibleResource(userId, menuId);
    }
}
