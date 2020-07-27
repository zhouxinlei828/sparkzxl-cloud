package com.sparksys.activiti.interfaces.controller.driver;

import com.sparksys.activiti.application.service.driver.IActivitiDriverService;
import com.sparksys.activiti.interfaces.dto.driver.DriveProcessDTO;
import com.sparksys.activiti.interfaces.dto.process.ProcessNextTaskDTO;
import com.sparksys.activiti.interfaces.dto.process.UserNextTask;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 流程驱动管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:29:54
 */
@RestController
@RequestMapping("/act/driver/")
@ResponseResult
@WebLog
@Api(tags = "流程驱动管理")
public class ActivitiDriverController {

    private final IActivitiDriverService activitiDriverService;

    public ActivitiDriverController(IActivitiDriverService activitiDriverService) {
        this.activitiDriverService = activitiDriverService;
    }

    @ApiOperation(value = "驱动activiti流程")
    @PostMapping("driveProcess")
    public boolean driveProcess(@RequestBody @Validated DriveProcessDTO driveProcessDTO) {
        return activitiDriverService.driveProcess(driveProcessDTO);
    }

    @ApiOperation(value = "获取下一步任务详情")
    @PostMapping("nextUserTask")
    public List<UserNextTask> getNextUserTask(@RequestBody @Validated ProcessNextTaskDTO processNextTaskDTO) {
        return activitiDriverService.getNextUserTask(processNextTaskDTO);
    }

}
