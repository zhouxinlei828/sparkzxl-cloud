package com.github.sparkzxl.authorization.interfaces.controller.tenant;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.ITenantInfoService;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 租户管理
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 16:21:52
 */
@RestController
@ResponseResult
@Api(tags = "租户管理")
@RequestMapping("/tenant")
public class TenantController {

    private final ITenantInfoService tenantService;

    public TenantController(ITenantInfoService tenantService) {
        this.tenantService = tenantService;
    }

    @ApiOperation("租户列表")
    @GetMapping("/list")
    public List<TenantInfo> tenantList() {
        return tenantService.list();
    }

    @ApiOperation("租户分页")
    @PostMapping("/page")
    public PageInfo<TenantInfo> getTenantPageList(@RequestBody TenantPageDTO tenantPageDTO) {
        return tenantService.getTenantPageList(tenantPageDTO);
    }

    @ApiOperation("新增租户信息")
    @PostMapping("/save")
    public boolean saveTenant(@Validated @RequestBody TenantSaveDTO tenantSaveDTO) {
        return tenantService.saveTenant(tenantSaveDTO);
    }

    @ApiOperation("修改租户信息")
    @PutMapping("/update")
    public boolean updateTenant(@Validated @RequestBody TenantUpdateDTO tenantUpdateDTO) {
        return tenantService.updateTenant(tenantUpdateDTO);
    }

    @ApiOperation("删除租户信息")
    @DeleteMapping("/delete")
    public boolean deleteTenant(@RequestBody DeleteDTO<Long> deleteDTO) {
        return tenantService.deleteBatchTenant(deleteDTO.getIds());
    }

}
