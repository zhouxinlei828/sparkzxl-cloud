package com.github.sparkzxl.authorization.interfaces.controller.core;


import com.github.sparkzxl.authorization.application.service.ICoreOrgService;
import com.github.sparkzxl.authorization.infrastructure.entity.CoreOrg;
import com.github.sparkzxl.authorization.interfaces.dto.org.OrgSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.org.OrgUpdateDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 组织 前端控制器
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:40:59
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "组织管理")
@RequestMapping("/org")
public class OrgController {

    private final ICoreOrgService coreOrgService;

    public OrgController(ICoreOrgService coreOrgService) {
        this.coreOrgService = coreOrgService;
    }

    @ApiOperation("查询组织列表")
    @GetMapping("/orgs")
    public List<CoreOrg> getCoreOrgList(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "status", required = false) Boolean status) {
        return coreOrgService.getCoreOrgList(name, status);
    }

    @ApiOperation("新增组织")
    @PostMapping("/org")
    public boolean saveCoreOrg(@Validated @RequestBody OrgSaveDTO orgSaveDTO) {
        return coreOrgService.saveCoreOrg(orgSaveDTO);
    }

    @ApiOperation("修改组织")
    @PutMapping("/org")
    public boolean updateCoreOrg(@Validated @RequestBody OrgUpdateDTO orgUpdateDTO) {
        return coreOrgService.updateCoreOrg(orgUpdateDTO);
    }

    @ApiOperation("删除组织")
    @DeleteMapping("/org")
    public boolean deleteCoreOrg(@RequestParam("id") Long id) {
        return coreOrgService.deleteCoreOrg(id);
    }

    @ApiOperation("批量删除组织")
    @DeleteMapping("/org/batch")
    public boolean deleteBatchCoreOrg(@RequestBody DeleteDTO deleteDTO) {
        return coreOrgService.deleteBatchCoreOrg(deleteDTO.getIds());
    }
}
