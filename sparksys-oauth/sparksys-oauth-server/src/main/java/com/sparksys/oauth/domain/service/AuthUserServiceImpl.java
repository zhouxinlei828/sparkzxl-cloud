package com.sparksys.oauth.domain.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import com.sparksys.core.constant.CacheKey;
import com.sparksys.core.entity.GlobalAuthUser;
import com.sparksys.core.utils.ListUtils;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.database.utils.PageInfoUtils;
import com.sparksys.oauth.infrastructure.entity.*;
import com.sparksys.security.entity.AuthUserDetail;
import com.sparksys.oauth.application.service.IAuthUserService;
import com.sparksys.oauth.domain.constant.AuthorizationConstant;
import com.sparksys.oauth.domain.repository.IAuthUserRepository;
import com.sparksys.oauth.infrastructure.convert.AuthUserConvert;
import com.sparksys.oauth.infrastructure.mapper.AuthUserMapper;
import com.sparksys.oauth.interfaces.dto.user.AuthUserDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserSaveDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserStatusDTO;
import com.sparksys.oauth.interfaces.dto.user.AuthUserUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * description: 用户查询 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:57
 */
@Service
@Slf4j
public class AuthUserServiceImpl extends AbstractSuperCacheServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

    @Autowired
    private IAuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean saveAuthUser(Long contextUserId, AuthUserSaveDTO authUserSaveDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserSaveDTO);
        authUser.setStatus(true);
        String password = passwordEncoder.encode(authUserSaveDTO.getPassword());
        authUser.setPassword(password);
        authUser.setCreateUser(contextUserId);
        authUser.setUpdateUser(contextUserId);
        return save(authUser);
    }

    @Override
    public boolean updateAuthUser(Long contextUserId, AuthUserUpdateDTO authUserUpdateDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserUpdateDTO);
        authUser.setUpdateUser(contextUserId);
        return updateById(authUser);
    }

    @Override
    public boolean deleteAuthUser(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateAuthUserStatus(Long contextUserId, AuthUserStatusDTO authUserStatusDTO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertAuthUserDO(authUserStatusDTO);
        authUser.setUpdateUser(contextUserId);
        return updateById(authUser);
    }

    @Override
    public PageInfo<AuthUserDTO> listByPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthUser> authUserList = authUserRepository.listByName(name);
        List<AuthUserDTO> authUsers =
                authUserList.stream().map(AuthUserConvert.INSTANCE::convertAuthUserDTO).collect(Collectors.toList());
        return PageInfoUtils.pageInfo(authUsers);
    }

    @Override
    public AuthUserDTO getAuthUser(Long id) {
        AuthUser authUser = getByIdCache(id);
        return AuthUserConvert.INSTANCE.convertAuthUserDTO(authUser);
    }

    @Override
    public boolean resetPassErrorNum(Long id) {
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        return updateById(authUser);
    }

    @Override
    public boolean resetPassErrorNum(String account) {
        AuthUser authUser = new AuthUser();
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        UpdateWrapper<AuthUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("account", account);
        return update(authUser, userUpdateWrapper);
    }

    @Override
    public boolean incrPasswordErrorNum(Long id) {
        return authUserRepository.incrPasswordErrorNumById(id);
    }

    @Override
    public boolean incrPasswordErrorNum(String account) {
        return authUserRepository.incrPasswordErrorNumByAccount(account);
    }

    @Override
    public AuthUserDetail getAuthUserDetail(String username) {
        AuthUser authUser = authUserRepository.selectByAccount(username);
        if (ObjectUtils.isNotEmpty(authUser)) {
            GlobalAuthUser globalAuthUser = AuthUserConvert.INSTANCE.convertGlobalAuthUser(authUser);
            List<String> userPermissions = authUserRepository.getAuthUserPermissions(authUser.getId());
            globalAuthUser.setPermissions(userPermissions);
            return new AuthUserDetail(globalAuthUser);
        }
        return null;
    }

    @Override
    public Set<String> getAuthUserPermissions(Long id) {
        List<String> userPermissions = authUserRepository.getAuthUserPermissions(id);
        if (ListUtils.isNotEmpty(userPermissions)) {
            return new HashSet<>(userPermissions);
        }
        return Sets.newHashSet();
    }

    @Override
    protected String getRegion() {
        return CacheKey.USER;
    }

    @Override
    public UserInfo getCurrentUser(String username) {
        AuthUser authUser = authUserRepository.selectByAccount(username);
        UserInfo userInfo = AuthUserConvert.INSTANCE.converUserInfo(authUser);
        List<Map<String, Object>> tagList = new ArrayList<>();
        Map<String, Object> tagMap = new HashMap<>(2);
        tagMap.put("key", 1);
        tagMap.put("label", "很有想法的");
        tagList.add(tagMap);
        userInfo.setTags(tagList);

        Map<String, Object> tagMap2 = new HashMap<>(2);
        tagMap2.put("key", 1);
        tagMap2.put("label", "专注设计");
        tagList.add(tagMap2);
        userInfo.setTags(tagList);

        Map<String, Object> tagMap3 = new HashMap<>(2);
        tagMap3.put("key", 1);
        tagMap3.put("label", "海纳百川");
        tagList.add(tagMap3);
        userInfo.setTags(tagList);

        City city = new City();
        city.setId(330100L);
        city.setName("杭州市");
        Province province = new Province();
        province.setId(330000L);
        province.setName("浙江省");

        Geographic geographic = new Geographic();
        geographic.setProvince(province);
        geographic.setCity(city);
        userInfo.setGeographic(geographic);

        userInfo.setNotifyCount(20L);
        userInfo.setUnreadCount(12L);
        return userInfo;
    }
}
