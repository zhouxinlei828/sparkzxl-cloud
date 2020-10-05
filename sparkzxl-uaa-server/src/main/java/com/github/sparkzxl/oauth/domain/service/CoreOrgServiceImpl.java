package com.github.sparkzxl.oauth.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.sparkzxl.oauth.application.service.IAuthUserService;
import com.github.sparkzxl.oauth.application.service.ICoreOrgService;
import com.github.sparkzxl.oauth.application.service.IRoleOrgService;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.convert.CoreOrgConvert;
import com.github.sparkzxl.oauth.infrastructure.entity.CoreOrg;
import com.github.sparkzxl.oauth.infrastructure.mapper.CoreOrgMapper;
import com.github.sparkzxl.oauth.interfaces.dto.org.OrgSaveDTO;
import com.github.sparkzxl.oauth.interfaces.dto.org.OrgUpdateDTO;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.database.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * description: 组织 服务实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:37:19
 */
@Service
public class CoreOrgServiceImpl extends AbstractSuperCacheServiceImpl<CoreOrgMapper, CoreOrg> implements ICoreOrgService {

    @Autowired
    private IRoleOrgService roleOrgService;

    @Autowired
    private IAuthUserService authUserService;

    @Override
    public List<CoreOrg> getCoreOrgList(String name, Boolean status) {
        QueryWrapper<CoreOrg> orgQueryWrapper = new QueryWrapper<>();
        Optional.ofNullable(name).ifPresent(value -> orgQueryWrapper.likeRight("label", value));
        Optional.ofNullable(status).ifPresent(value -> orgQueryWrapper.likeRight("status", value));
        List<CoreOrg> coreOrgList = list(orgQueryWrapper);
        return TreeUtil.buildTree(coreOrgList);
    }

    @Override
    public boolean saveCoreOrg(Long userId, OrgSaveDTO orgSaveDTO) {
        CoreOrg coreOrg = CoreOrgConvert.INSTANCE.convertCoreOrg(orgSaveDTO);
        coreOrg.setCreateUser(userId);
        coreOrg.setUpdateUser(userId);
        return save(coreOrg);
    }

    @Override
    public boolean updateCoreOrg(Long userId, OrgUpdateDTO orgUpdateDTO) {
        CoreOrg coreOrg = CoreOrgConvert.INSTANCE.convertCoreOrg(orgUpdateDTO);
        coreOrg.setUpdateUser(userId);
        return updateById(coreOrg);
    }

    @Override
    public boolean deleteCoreOrg(Long id) {
        roleOrgService.deleteRoleOrgByOrgId(id);
        authUserService.deleteOrgId(id);
        return removeById(id);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.ORG;
    }
}
