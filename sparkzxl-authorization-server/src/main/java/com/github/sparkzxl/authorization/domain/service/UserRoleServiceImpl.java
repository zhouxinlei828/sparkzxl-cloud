package com.github.sparkzxl.authorization.domain.service;

import com.github.sparkzxl.authorization.application.service.IUserRoleService;
import com.github.sparkzxl.authorization.domain.model.aggregates.RoleResource;
import com.github.sparkzxl.authorization.domain.model.vo.RoleResourceVO;
import com.github.sparkzxl.authorization.domain.repository.IRoleAuthorityRepository;
import com.github.sparkzxl.authorization.domain.repository.IUserRoleRepository;
import com.github.sparkzxl.authorization.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authorization.infrastructure.convert.AuthRoleConvert;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authorization.infrastructure.entity.UserRole;
import com.github.sparkzxl.authorization.infrastructure.mapper.UserRoleMapper;
import com.github.sparkzxl.authorization.interfaces.dto.role.RoleUserDTO;
import com.github.sparkzxl.authorization.interfaces.dto.role.RoleUserDeleteDTO;
import com.github.sparkzxl.authorization.interfaces.dto.role.RoleUserSaveDTO;
import com.github.sparkzxl.database.base.service.impl.SuperCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * description: 账号角色绑定 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:01:40
 */
@Service
public class UserRoleServiceImpl extends SuperCacheServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IRoleAuthorityRepository roleAuthorityRepository;

    @Override
    protected String getRegion() {
        return CacheConstant.USER_ROLE;
    }

    @Override
    public boolean saveAuthRoleUser(RoleUserSaveDTO roleUserSaveDTO) {
        return userRoleRepository.saveAuthRoleUser(roleUserSaveDTO.getRoleId(), roleUserSaveDTO.getUserIds());
    }

    @Override
    public boolean deleteAuthRoleUser(RoleUserDeleteDTO roleUserDeleteDTO) {
        return userRoleRepository.deleteAuthRoleUser(roleUserDeleteDTO.getId(), roleUserDeleteDTO.getUserIds());
    }

    @Override
    public RoleUserDTO getRoleUserList(Long roleId) {
        RoleUserDTO roleUserDTO = new RoleUserDTO();
        roleUserDTO.setId(roleId);
        List<AuthUser> authUsers = userRoleRepository.getRoleUserList(roleId);
        Optional.ofNullable(authUsers).ifPresent(x -> roleUserDTO.setAuthUsers(authUsers));
        return roleUserDTO;
    }

    @Override
    public RoleResourceVO getRoleResource(Long roleId) {
        RoleResource roleResource = roleAuthorityRepository.getRoleResource(roleId);
        return AuthRoleConvert.INSTANCE.convertRoleResourceVO(roleResource);
    }
}
