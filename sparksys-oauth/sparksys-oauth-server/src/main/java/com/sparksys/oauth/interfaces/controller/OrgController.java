package com.sparksys.oauth.interfaces.controller;


import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.oauth.application.service.ICoreOrgService;
import com.sparksys.oauth.infrastructure.entity.CoreOrg;
import com.sparksys.oauth.interfaces.dto.org.OrgSaveDTO;
import com.sparksys.oauth.interfaces.dto.org.OrgUpdateDTO;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
public class OrgController {

    private final ICoreOrgService coreOrgService;

    public OrgController(ICoreOrgService coreOrgService) {
        this.coreOrgService = coreOrgService;
    }

    @ApiOperation("查询组织列表")
    @GetMapping("/orgs")
    public List<CoreOrg> getCoreOrgList(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "status", required = false) Boolean status) {
        return coreOrgService.getCoreOrgList(name,status);
    }

    @ApiOperation("新增组织")
    @PostMapping("/org")
    public boolean saveCoreOrg(@ApiIgnore AuthUserInfo authUserInfo, @Validated @RequestBody OrgSaveDTO orgSaveDTO) {
        return coreOrgService.saveCoreOrg(authUserInfo.getId(), orgSaveDTO);
    }

    @ApiOperation("修改组织")
    @PutMapping("/org")
    public boolean updateCoreOrg(@ApiIgnore AuthUserInfo authUserInfo, @Validated @RequestBody OrgUpdateDTO orgUpdateDTO) {
        return coreOrgService.updateCoreOrg(authUserInfo.getId(), orgUpdateDTO);
    }

    @ApiOperation("删除组织")
    @DeleteMapping("/org/{id}")
    public boolean deleteCoreOrg(@PathVariable("id") Long id){
        return coreOrgService.deleteCoreOrg(id);
    }
}
