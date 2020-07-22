package com.sparksys.oauth.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.database.annonation.InjectionResult;
import com.sparksys.oauth.domain.bo.AuthUserBO;
import com.sparksys.oauth.domain.repository.IAuthUserRepository;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.mapper.AuthUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * description：用户仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020/6/5 8:45 下午
 */
@Repository
public class AuthUserRepository implements IAuthUserRepository {

    @Autowired
    public AuthUserMapper authUserMapper;

    @Override
    public AuthUser selectById(Long id) {
        return authUserMapper.selectById(id);
    }

    @Override
    @InjectionResult
    public AuthUser selectByAccount(String account) {
        QueryWrapper<AuthUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        queryWrapper.eq("status", 1);
        return authUserMapper.selectOne(queryWrapper);
    }

    @Override
    @InjectionResult
    public List<AuthUser> findAuthUserList(AuthUserBO authUserBO) {
        QueryWrapper<AuthUser> userQueryWrapper = new QueryWrapper<>();
        Optional<String> accountOptional = Optional.ofNullable(authUserBO.getAccount());
        Optional<String> nameOptional = Optional.ofNullable(authUserBO.getName());
        Optional<String> emailOptional = Optional.ofNullable(authUserBO.getEmail());
        Optional<String> mobileOptional = Optional.ofNullable(authUserBO.getMobile());
        Optional<Integer> sexOptional = Optional.ofNullable(authUserBO.getSex());
        Optional<Boolean> statusOptional = Optional.ofNullable(authUserBO.getStatus());
        accountOptional.ifPresent((value) -> userQueryWrapper.eq("account", authUserBO.getAccount()));
        nameOptional.ifPresent((value) -> userQueryWrapper.likeRight("name", authUserBO.getName()));
        emailOptional.ifPresent((value) -> userQueryWrapper.eq("email", authUserBO.getEmail()));
        mobileOptional.ifPresent((value) -> userQueryWrapper.eq("mobile", authUserBO.getMobile()));
        sexOptional.ifPresent((value) -> userQueryWrapper.eq("sex", authUserBO.getSex()));
        statusOptional.ifPresent((value) -> userQueryWrapper.eq("status", authUserBO.getStatus()));
        return authUserMapper.selectList(userQueryWrapper);
    }

    @Override
    public boolean incrPasswordErrorNumById(Long id) {
        return authUserMapper.incrPasswordErrorNumById(id) == 1;
    }

    @Override
    public List<String> getAuthUserPermissions(Long id) {
        return authUserMapper.getAuthUserPermissions(id);
    }

    @Override
    public boolean incrPasswordErrorNumByAccount(String account) {
        return authUserMapper.incrPasswordErrorNumByAccount(account) == 1;
    }
}
