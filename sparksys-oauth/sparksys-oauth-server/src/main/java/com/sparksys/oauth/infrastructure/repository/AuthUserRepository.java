package com.sparksys.oauth.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.database.annonation.InjectionResult;
import com.sparksys.database.utils.PageInfoUtils;
import com.sparksys.oauth.domain.bo.AuthUserBO;
import com.sparksys.oauth.domain.repository.IAuthUserRepository;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public PageInfo<AuthUser> findAuthUserList(int pageNum, int pageSize, AuthUserBO authUserBO) {
        QueryWrapper<AuthUser> userQueryWrapper = new QueryWrapper<>();
        Optional.ofNullable(authUserBO.getAccount()).ifPresent((value) -> userQueryWrapper.eq("account", authUserBO.getAccount()));
        Optional.ofNullable(authUserBO.getName()).ifPresent((value) -> userQueryWrapper.likeRight("name", authUserBO.getName()));
        Optional.ofNullable(authUserBO.getEmail()).ifPresent((value) -> userQueryWrapper.eq("email", authUserBO.getEmail()));
        Optional.ofNullable(authUserBO.getMobile()).ifPresent((value) -> userQueryWrapper.eq("mobile", authUserBO.getMobile()));
        Optional.ofNullable(authUserBO.getSex()).ifPresent((value) -> userQueryWrapper.eq("sex", authUserBO.getSex()));
        Optional.ofNullable(authUserBO.getStatus()).ifPresent((value) -> userQueryWrapper.eq("status", authUserBO.getStatus()));
        PageHelper.startPage(pageNum, pageSize);
        return PageInfoUtils.pageInfo(authUserMapper.selectList(userQueryWrapper));
    }

    @Override
    public void incrPasswordErrorNumById(Long id) {
        authUserMapper.incrPasswordErrorNumById(id);
    }

    @Override
    public List<String> getAuthUserPermissions(Long id) {
        return authUserMapper.getAuthUserPermissions(id);
    }

    @Override
    public void incrPasswordErrorNumByAccount(String account) {
        authUserMapper.incrPasswordErrorNumByAccount(account);
    }
}
