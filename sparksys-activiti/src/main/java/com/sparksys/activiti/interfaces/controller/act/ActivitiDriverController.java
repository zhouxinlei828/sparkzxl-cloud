package com.sparksys.activiti.interfaces.controller.act;

import com.sparksys.activiti.application.service.driver.IActivitiDriverService;
import com.sparksys.activiti.interfaces.dto.ApplyDatasetInfo;
import com.sparksys.activiti.interfaces.dto.DriveProcessDTO;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * description: 流程驱动管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:29:54
 */
@RestController
@RequestMapping("/activiti/driver/")
@ResponseResult
@WebLog
@Api(tags = "流程驱动管理")
public class ActivitiDriverController {

    private final IActivitiDriverService activitiDriverService;

    public ActivitiDriverController(IActivitiDriverService activitiDriverService) {
        this.activitiDriverService = activitiDriverService;
    }

    @ApiOperation(value = "驱动activiti流程")
    @PostMapping("applyDataSet")
    public void driveProcess(@ApiIgnore AuthUserInfo authUserInfo, @RequestBody @Validated DriveProcessDTO driveProcessDTO) {
        activitiDriverService.driveProcess(authUserInfo.getId(), driveProcessDTO);
    }

}
