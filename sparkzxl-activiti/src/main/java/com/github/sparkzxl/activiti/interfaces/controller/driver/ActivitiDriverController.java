package com.github.sparkzxl.activiti.interfaces.controller.driver;

import com.github.sparkzxl.activiti.application.service.driver.IActivitiDriverService;
import com.github.sparkzxl.activiti.interfaces.dto.driver.DriveProcessDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessNextTaskDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.UserNextTask;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
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
