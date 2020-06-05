package com.sparksys.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.authorization.infrastructure.convert.AuthUserConvert;
import com.sparksys.authorization.infrastructure.mapper.AuthUserMapper;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.domain.repository.IAuthUserRepository;
import com.sparksys.authorization.infrastructure.po.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * projectName：sparksys-cloud
 * packageName：com.sparksys.authorization.infrastructure.repository
 * description：
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
        queryWrapper.eq("account",username);
        queryWrapper.eq("status",1);
        return authUserMapper.selectOne(queryWrapper);
    }

    @Override
    public AuthUserDO selectByCondition(AuthUserDO authUserDO) {
        QueryWrapper<AuthUserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",authUserDO.getAccount());
        queryWrapper.eq("status",1);
        return authUserMapper.selectOne(queryWrapper);
    }
}
