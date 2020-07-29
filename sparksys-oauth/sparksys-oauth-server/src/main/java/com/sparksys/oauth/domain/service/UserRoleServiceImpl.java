package com.sparksys.oauth.domain.service;

import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.oauth.domain.repository.IUserRoleRepository;
import com.sparksys.oauth.infrastructure.constant.CacheConstant;
import com.sparksys.oauth.infrastructure.entity.UserRole;
import com.sparksys.oauth.infrastructure.mapper.UserRoleMapper;
import com.sparksys.oauth.application.service.IUserRoleService;
import com.sparksys.oauth.interfaces.dto.role.RoleUserDTO;
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
