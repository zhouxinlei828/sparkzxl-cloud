package com.github.sparkzxl.authorization.interfaces.controller.tenant;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.ITenantInfoService;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.database.dto.PageParams;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 租户管理
 *
 * @author charles.zhou
 * @date   2021-02-02 16:21:52
 */
@RestController
@ResponseResult
@Api(tags = "租户管理")
@RequestMapping("/tenant")
public class TenantController extends SuperCacheController<ITenantInfoService, Long,
        TenantInfo, TenantSaveDTO, TenantUpdateDTO, TenantPageDTO, Object> {

    @Override
    public PageInfo<TenantInfo> page(PageParams<TenantPageDTO> params) {
        return baseService.getTenantPageList(params);
    }

    @Override
    public boolean save(TenantSaveDTO tenantSaveDTO) {
        return baseService.saveTenant(tenantSaveDTO);
    }

    @Override
    public boolean update(TenantUpdateDTO tenantUpdateDTO) {
        return baseService.updateTenant(tenantUpdateDTO);
    }

    @Override
    public boolean delete(DeleteDTO<Long> deleteDTO) {
        return baseService.deleteBatchTenant(deleteDTO.getIds());
    }

}
