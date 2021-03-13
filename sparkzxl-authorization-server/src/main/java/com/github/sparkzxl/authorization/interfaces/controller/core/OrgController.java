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
import org.springframework.beans.factory.annotation.Autowired;
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

    private ICoreOrgService coreOrgService;

    @Autowired
    public void setCoreOrgService(ICoreOrgService coreOrgService) {
        this.coreOrgService = coreOrgService;
    }

    @ApiOperation("组织树查询")
    @GetMapping("/tree")
    public List<CoreOrg> getCoreOrgTree(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "status", required = false) Boolean status) {
        return coreOrgService.getCoreOrgTree(name, status);
    }

    @ApiOperation("新增组织")
    @PostMapping("/save")
    public boolean saveCoreOrg(@Validated @RequestBody OrgSaveDTO orgSaveDTO) {
        return coreOrgService.saveCoreOrg(orgSaveDTO);
    }

    @ApiOperation("修改组织")
    @PutMapping("/update")
    public boolean updateCoreOrg(@Validated @RequestBody OrgUpdateDTO orgUpdateDTO) {
        return coreOrgService.updateCoreOrg(orgUpdateDTO);
    }

    @ApiOperation("删除组织")
    @DeleteMapping("/delete")
    public boolean deleteCoreOrg(@RequestBody DeleteDTO<Long> deleteDTO) {
        return coreOrgService.deleteBatchCoreOrg(deleteDTO.getIds());
    }
}
