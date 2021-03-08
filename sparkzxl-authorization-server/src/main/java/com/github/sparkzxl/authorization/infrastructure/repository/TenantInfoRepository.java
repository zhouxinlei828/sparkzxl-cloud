package com.github.sparkzxl.authorization.infrastructure.repository;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.domain.repository.*;
import com.github.sparkzxl.authorization.infrastructure.entity.*;
import com.github.sparkzxl.authorization.infrastructure.enums.SexEnum;
import com.github.sparkzxl.authorization.infrastructure.mapper.*;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.database.entity.SuperEntity;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description: 租户仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-14 10:13:48
 */
@Repository
public class TenantInfoRepository implements ITenantInfoRepository {

    @Autowired
    private TenantInfoMapper tenantMapper;
    @Autowired
    private IIdSegmentRepository segmentRepository;
    @Autowired
    private IAuthUserRepository authUserRepository;
    @Autowired
    private IAuthRoleRepository authRoleRepository;
    @Autowired
    private IAuthMenuRepository authMenuRepository;
    @Autowired
    private IAuthResourceRepository resourceRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IRoleAuthorityRepository roleAuthorityRepository;
    @Autowired
    private AuthApplicationMapper tenantClientMapper;
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;
    @Autowired
    private CoreStationMapper stationMapper;
    @Autowired
    private CoreOrgMapper orgMapper;
    @Autowired
    private CommonDictionaryMapper dictionaryMapper;
    @Autowired
    private CommonDictionaryItemMapper dictionaryItemMapper;

    @Override
    public PageInfo<TenantInfo> getTenantPageList(int pageNum, int pageSize, String code, String name) {
        LambdaQueryWrapper<TenantInfo> tenantLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            tenantLambdaQueryWrapper.eq(TenantInfo::getCode, code);
        }
        if (StringUtils.isNotEmpty(name)) {
            tenantLambdaQueryWrapper.likeLeft(TenantInfo::getName, name);
        }
        tenantLambdaQueryWrapper.orderByAsc(TenantInfo::getCode);
        PageHelper.startPage(pageNum, pageSize);
        List<TenantInfo> tenantList = tenantMapper.selectList(tenantLambdaQueryWrapper);
        // 初始化管理员账户
        AuthUser authUser = new AuthUser();
        authUser.setAccount("admin");
        authUser.setOriginalPassword("123456");
        authUser.setName("管理员");
        tenantList.forEach(tenantInfo -> tenantInfo.setAdminUser(authUser));
        return PageInfoUtils.pageInfo(tenantList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveTenant(TenantInfo tenant) {
        String tenantCode = segmentRepository.getIdSegment("tenant_code").toString();
        tenant.setCode(tenantCode);
        tenantMapper.insert(tenant);
        initTenantData(tenantCode);
        return true;
    }

    public void initTenantData(String tenantCode) {
        BaseContextHandler.setTenant(tenantCode);
        // 初始化管理员账户
        AuthUser authUser = new AuthUser();
        authUser.setAccount("admin");
        authUser.setPassword("123456");
        authUser.setName("管理员");
        authUser.setTenantCode(tenantCode);
        authUser.setSex(SexEnum.MAN);
        authUser.setStatus(true);
        authUserRepository.saveAuthUserInfo(authUser);
        Long userId = authUser.getId();
        // 初始化管理员角色
        AuthRole authRole = new AuthRole();
        authRole.setCode("ADMIN");
        authRole.setName("管理员");
        authRole.setDescribe("内置管理员");
        authRole.setReadonly(true);
        authRole.setDsType("ALL");
        authRole.setStatus(true);
        authRoleRepository.saveRole(authRole);
        Long roleId = authRole.getId();
        userRoleRepository.saveAuthRoleUser(roleId, Lists.newArrayList(userId));
        // 初始化菜单资源
        initMenuData(tenantCode);
        List<AuthMenu> authMenuList = authMenuRepository.findAuthMenuList();
        Set<Long> menuIds = authMenuList.stream().map(SuperEntity::getId).collect(Collectors.toSet());
        List<AuthResource> authResources = resourceRepository.authResourceList();
        Set<Long> resourceIds = authResources.stream().map(SuperEntity::getId).collect(Collectors.toSet());
        roleAuthorityRepository.saveRoleAuthorityBatch(roleId, resourceIds, menuIds);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void initMenuData(String tenantCode) {
        String menuJsonStr = ResourceUtil.readUtf8Str("menu.json");
        List<AuthMenu> authMenus = JSONArray.parseArray(menuJsonStr, AuthMenu.class);
        authMenuRepository.saveAuthMenus(authMenus,tenantCode);
    }

    @Override
    public boolean updateTenant(TenantInfo tenant) {
        return tenantMapper.updateById(tenant) != 0;
    }

    @Override
    public boolean deleteTenant(Long tenantId) {
        TenantInfo tenantInfo = tenantMapper.selectById(tenantId);
        String tenantCode = tenantInfo.getCode();
        authUserRepository.deleteTenantUser(tenantCode);
        authRoleRepository.deleteAuthRole(tenantCode);
        resourceRepository.deleteTenantResource(tenantCode);
        authMenuRepository.deleteTenantMenu(tenantCode);
        List<AuthApplication> tenantClientList = tenantClientMapper.selectList(new LambdaQueryWrapper<AuthApplication>()
                .eq(AuthApplication::getTenantCode, tenantCode));
        if (CollectionUtils.isNotEmpty(tenantClientList)) {
            List<String> clientIdList = tenantClientList.stream().map(AuthApplication::getClientId).collect(Collectors.toList());
            oauthClientDetailsMapper.deleteBatchIds(clientIdList);
        }
        tenantClientMapper.deleteTenantClient(tenantCode);
        stationMapper.deleteTenantStation(tenantCode);
        orgMapper.deleteTenantOrg(tenantCode);
        dictionaryMapper.deleteTenantDictionary(tenantCode);
        dictionaryItemMapper.deleteTenantDictionaryItem(tenantCode);
        return tenantMapper.deleteById(tenantId) != 0;
    }
}
