package com.sparksys.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.authorization.infrastructure.mapper.AuthUserMapper;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
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
    public AuthUserDO selectByUserName(String username) {
        QueryWrapper<AuthUserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", username);
        queryWrapper.eq("status", 1);
        return authUserMapper.selectOne(queryWrapper);
    }

    @Override
    public AuthUserDO selectByCondition(AuthUserDO authUserDO) {
        QueryWrapper<AuthUserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", authUserDO.getAccount());
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
}
