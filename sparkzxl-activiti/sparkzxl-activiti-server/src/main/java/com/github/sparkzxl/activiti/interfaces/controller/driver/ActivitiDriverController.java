package com.github.sparkzxl.activiti.interfaces.controller.driver;

import com.github.sparkzxl.activiti.api.ActivitiDriverApi;
import com.github.sparkzxl.activiti.application.service.act.IProcessHistoryService;
import com.github.sparkzxl.activiti.application.service.driver.IActivitiDriverService;
import com.github.sparkzxl.activiti.dto.ActivitiDataDTO;
import com.github.sparkzxl.activiti.dto.DriverProcessDTO;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.dto.UserNextTask;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessNextTaskDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * description: 流程驱动管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:29:54
 */
@AllArgsConstructor
@RestController
@RequestMapping("/act/driver")
@ResponseResult
@WebLog
@Api(tags = "流程驱动管理")
public class ActivitiDriverController implements ActivitiDriverApi {

    private final IActivitiDriverService activitiDriverService;
    private final IProcessHistoryService processHistoryService;

    @Override
    public DriverResult driverProcess(DriverProcessDTO driverProcessDTO) {
        return activitiDriverService.driverProcess(driverProcessDTO);
    }

    @Override
    public ActivitiDataDTO findActivitiData(String businessId, String processDefinitionKey) {
        return activitiDriverService.findActivitiData(businessId, processDefinitionKey);
    }

    @Override
    public List<ActivitiDataDTO> findActivitiDataList(List<String> businessIds, String processDefinitionKey) {
        return activitiDriverService.findActivitiDataList(businessIds, processDefinitionKey);
    }

    @ApiOperation(value = "获取下一步任务详情")
    @PostMapping("nextUserTask")
    public List<UserNextTask> getNextUserTask(@RequestBody @Validated ProcessNextTaskDTO processNextTaskDTO) {
        return activitiDriverService.getNextUserTask(processNextTaskDTO);
    }

    @ApiOperation("获取流程图并显示")
    @GetMapping("/history/processImg/{processInstanceId}")
    public void getProcessImg(@ApiParam("流程实例id") @PathVariable String processInstanceId, HttpServletResponse response) {
        processHistoryService.getProcessImage(processInstanceId, response);
    }

    @Override
    public boolean suspendProcess(String businessId) {
        return activitiDriverService.suspendProcess(businessId);
    }
}
