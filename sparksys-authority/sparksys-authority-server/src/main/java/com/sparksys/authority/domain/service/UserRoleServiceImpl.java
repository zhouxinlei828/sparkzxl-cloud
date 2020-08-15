package com.sparksys.authority.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.authority.application.service.IUserRoleService;
import com.sparksys.authority.domain.repository.IUserRoleRepository;
import com.sparksys.authority.infrastructure.constant.CacheConstant;
import com.sparksys.authority.infrastructure.entity.UserRole;
import com.sparksys.authority.infrastructure.mapper.UserRoleMapper;
import com.sparksys.authority.interfaces.dto.role.RoleUserDTO;
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
