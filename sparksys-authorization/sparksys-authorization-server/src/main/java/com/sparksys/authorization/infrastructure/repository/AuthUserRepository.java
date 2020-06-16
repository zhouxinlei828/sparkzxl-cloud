package com.sparksys.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.infrastructure.mapper.AuthUserMapper;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

/**
 * description：用户仓储层实现类
 *
 * @Author zhouxinlei
 * @Date 2020/6/5 8:45 下午
 */
@Repository
public class AuthUserRepository implements IAuthUserRepository {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public AuthUserDO selectById(Long id) {
        return authUserMapper.selectById(id);
    }

    @Override
    public AuthUserDO selectByUserName(String username) {
        QueryWrapper<AuthUserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", username);
        queryWrapper.eq("status", 1);
        return authUserMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean saveAuthUser(AuthUserDO authUserDO) {
        authUserDO.setStatus(true);
        return authUserMapper.insert(authUserDO) > 0;
    }

    @Override
    public boolean updateAuthUser(AuthUserDO authUserDO) {
        return authUserMapper.updateById(authUserDO) > 0;
    }

    @Override
    public boolean deleteAuthUser(Long id) {
        return authUserMapper.deleteById(id) > 0;
    }

    @Override
    public Page<AuthUserDO> listByPage(Page authUserDOPage, String name) {
        QueryWrapper<AuthUserDO> authUserDOQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equalsIgnoreCase(name)) {
            authUserDOQueryWrapper.like("name", name);
        }
        return authUserMapper.selectPage(authUserDOPage, authUserDOQueryWrapper);
    }
}
