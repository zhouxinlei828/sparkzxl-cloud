package com.github.sparkzxl.authority.domain.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Sets;
import com.github.sparkzxl.authority.infrastructure.entity.*;
import com.github.sparkzxl.authority.interfaces.dto.user.AuthUserDTO;
import com.github.sparkzxl.core.utils.ListUtils;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.authority.application.service.IAuthUserService;
import com.github.sparkzxl.authority.domain.repository.IAuthUserRepository;
import com.github.sparkzxl.authority.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.authority.infrastructure.convert.AuthUserConvert;
import com.github.sparkzxl.authority.infrastructure.mapper.AuthUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public AuthUserDTO getAuthUser(Long id) {
        return AuthUserConvert.INSTANCE.convertAuthUserDTO(getByIdCache(id));
    }

    @Override
    public void resetPassErrorNum(Long id) {
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        updateById(authUser);
    }

    @Override
    public void resetPassErrorNum(String account) {
        AuthUser authUser = new AuthUser();
        authUser.setPasswordErrorNum(0);
        authUser.setPasswordErrorLastTime(null);
        UpdateWrapper<AuthUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.lambda().eq(AuthUser::getAccount, account);
        update(authUser, userUpdateWrapper);
    }

    @Override
    public void incrPasswordErrorNum(Long id) {
        authUserRepository.incrPasswordErrorNumById(id);
    }

    @Override
    public void incrPasswordErrorNum(String account) {
        authUserRepository.incrPasswordErrorNumByAccount(account);
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
        return CacheConstant.USER;
    }

    @Override
    public UserInfo getCurrentUser(String username) {
        AuthUser authUser = authUserRepository.selectByAccount(username);
        UserInfo userInfo = AuthUserConvert.INSTANCE.convertUserInfo(authUser);
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

    @Override
    public List<UserNotices> getUserNotices() {
        String data = "[{\"id\":\"xxx1\",\"title\":\"Alipay\",\"logo\":\"https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE" +
                ".png\",\"description\":\"那是一种内在的东西，他们到达不了，也无法触及的\",\"updatedAt\":\"2020-07-24\",\"member\":\"科学搬砖组\",\"href\":\"\",\"memberLink\":\"\"},{\"id\":\"xxx2\",\"title\":\"Angular\",\"logo\":\"https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png\",\"description\":\"希望是一个好东西，也许是最好的，好东西是不会消亡的\",\"updatedAt\":\"2020-07-24\",\"member\":\"全组都是吴彦祖\",\"href\":\"\",\"memberLink\":\"\"},{\"id\":\"xxx3\",\"title\":\"Ant Design\",\"logo\":\"https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png\",\"description\":\"城镇中有那么多的酒馆，她却偏偏走进了我的酒馆\",\"updatedAt\":\"2020-07-24\",\"member\":\"中二少女团\",\"href\":\"\",\"memberLink\":\"\"},{\"id\":\"xxx4\",\"title\":\"Ant Design Pro\",\"logo\":\"https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png\",\"description\":\"那时候我只会想自己想要什么，从不想自己拥有什么\",\"updatedAt\":\"2020-07-23\",\"member\":\"程序员日常\",\"href\":\"\",\"memberLink\":\"\"},{\"id\":\"xxx5\",\"title\":\"Bootstrap\",\"logo\":\"https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png\",\"description\":\"凛冬将至\",\"updatedAt\":\"2020-07-23\",\"member\":\"高逼格设计天团\",\"href\":\"\",\"memberLink\":\"\"},{\"id\":\"xxx6\",\"title\":\"React\",\"description\":\"生命就像一盒巧克力，结果往往出人意料\",\"logo\":\"https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png\",\"updatedAt\":\"2020-07-23\",\"member\":\"骗你来学计算机\",\"href\":\"\",\"memberLink\":\"\"}]";
        return JSONArray.parseArray(data, UserNotices.class);
    }

    @Override
    public List<UserActivities> activities() {
        String data = "[{\"id\":\"trend-1\",\"updatedAt\":\"2020-07-25\",\"user\":{\"name\":\"曲丽丽\",\"avatar\":\"https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png\"},\"group\":{\"name\":\"高逼格设计天团\",\"link\":\"http://github.com/\"},\"project\":{\"name\":\"六月迭代\",\"link\":\"http://github.com/\"},\"template\":\"在 @{group} 新建项目 @{project}\"},{\"id\":\"trend-2\",\"updatedAt\":\"2020-07-25\",\"user\":{\"name\":\"付小小\",\"avatar\":\"https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png\"},\"group\":{\"name\":\"高逼格设计天团\",\"link\":\"http://github.com/\"},\"project\":{\"name\":\"六月迭代\",\"link\":\"http://github.com/\"},\"template\":\"在 @{group} 新建项目 @{project}\"},{\"id\":\"trend-3\",\"updatedAt\":\"2020-07-25\",\"user\":{\"name\":\"林东东\",\"avatar\":\"https://gw.alipayobjects.com/zos/rmsportal/psOgztMplJMGpVEqfcgF.png\"},\"group\":{\"name\":\"中二少女团\",\"link\":\"http://github.com/\"},\"project\":{\"name\":\"六月迭代\",\"link\":\"http://github.com/\"},\"template\":\"在 @{group} 新建项目 @{project}\"},{\"id\":\"trend-4\",\"updatedAt\":\"2020-07-25\",\"user\":{\"name\":\"周星星\",\"avatar\":\"https://gw.alipayobjects.com/zos/rmsportal/ZpBqSxLxVEXfcUNoPKrz.png\"},\"project\":{\"name\":\"5 月日常迭代\",\"link\":\"http://github.com/\"},\"template\":\"将 @{project} 更新至已发布状态\"},{\"id\":\"trend-5\",\"updatedAt\":\"2020-07-25\",\"user\":{\"name\":\"朱偏右\",\"avatar\":\"https://gw.alipayobjects.com/zos/rmsportal/laiEnJdGHVOhJrUShBaJ.png\"},\"project\":{\"name\":\"工程效能\",\"link\":\"http://github.com/\"},\"comment\":{\"name\":\"留言\",\"link\":\"http://github.com/\"},\"template\":\"在 @{project} 发布了 @{comment}\"},{\"id\":\"trend-6\",\"updatedAt\":\"2020-07-25\",\"user\":{\"name\":\"乐哥\",\"avatar\":\"https://gw.alipayobjects.com/zos/rmsportal/UrQsqscbKEpNuJcvBZBu.png\"},\"group\":{\"name\":\"程序员日常\",\"link\":\"http://github.com/\"},\"project\":{\"name\":\"品牌迭代\",\"link\":\"http://github.com/\"},\"template\":\"在 @{group} 新建项目 @{project}\"}]";
        return JSONArray.parseArray(data, UserActivities.class);
    }

    @Override
    public void deleteOrgId(Long id) {
        UpdateWrapper<AuthUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("org_id", null);
        userUpdateWrapper.eq("org_id", id);
        update(userUpdateWrapper);
    }

    @Override
    public void deleteUserRelation(List<Long> ids) {
        authUserRepository.deleteUserRelation(ids);
    }

    @Override
    public LoginAuthUser getLoginAuthUser(Long id) {
        return authUserRepository.getLoginAuthUser(id);
    }
}
