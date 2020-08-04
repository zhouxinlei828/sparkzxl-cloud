package com.sparksys.authorization.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authorization.application.service.IUserRoleService;
import com.sparksys.authorization.domain.repository.IUserRoleRepository;
import com.sparksys.authorization.infrastructure.constant.CacheConstant;
import com.sparksys.authorization.infrastructure.entity.UserRole;
import com.sparksys.authorization.infrastructure.mapper.UserRoleMapper;
import com.sparksys.authorization.interfaces.dto.role.RoleUserDTO;
import org.springframework.stereotype.Service;


/**
 * description: 账号角色绑定 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:01:40
 */
@Service
public class UserRoleServiceImpl extends AbstractSuperCacheServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    private final IUserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(IUserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean saveAuthRoleUser(RoleUserDTO roleUserDTO) {
        return userRoleRepository.saveAuthRoleUser(roleUserDTO.getId(),roleUserDTO.getUserIds());
    }

    @Override
    public boolean deleteAuthRoleUser(RoleUserDTO roleUserDTO) {
        return userRoleRepository.deleteAuthRoleUser(roleUserDTO.getId(),roleUserDTO.getUserIds());
    }

    @Override
    protected String getRegion() {
        return CacheConstant.USER_ROLE;
    }
}
