package com.github.sparkzxl.activiti.interfaces.controller.driver;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.application.service.act.IProcessHistoryService;
import com.github.sparkzxl.activiti.application.service.ext.IExtProcessStatusService;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessHistory;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import com.github.sparkzxl.activiti.interfaces.dto.act.InstancePageDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * description: 流程实例管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 14:53:25
 */
@RestController
@ResponseResult
@WebLog
@RequestMapping("/act/process")
@Api(tags = "流程实例管理")
public class ActProcessController {

    private final IProcessHistoryService processHistoryService;
    private final IExtProcessStatusService processTaskStatusService;

    public ActProcessController(IProcessHistoryService processHistoryService,
                                IExtProcessStatusService processTaskStatusService) {
        this.processHistoryService = processHistoryService;
        this.processTaskStatusService = processTaskStatusService;
    }

    @ApiOperation("分页查询流程列表")
    @GetMapping("/instances")
    public PageInfo<ProcessInstance> getProcessInstanceList(InstancePageDTO instancePageDTO) {
        return processTaskStatusService.getProcessInstanceList(instancePageDTO);
    }

    @ApiOperation("根据流程实例id获取流程历史")
    @GetMapping("/processHistoryByProcessInstanceId")
    public List<ProcessHistory> getProcessHistoryByProcessInstanceId(@ApiParam("流程实例id") @RequestParam("processInstanceId") String processInstanceId) throws ExecutionException,
            InterruptedException {
        return processHistoryService.getProcessHistoryByProcessInstanceId(processInstanceId);
    }

    @ApiOperation("根据业务主键获取流程历史")
    @GetMapping("/processHistoryByBusinessId")
    public List<ProcessHistory> getProcessHistoryByBusinessId(@ApiParam("业务主键") @RequestParam("businessId") String businessId) {
        return processHistoryService.getProcessHistoryByBusinessId(businessId);
    }

    @ApiOperation("获取流程图并显示")
    @GetMapping("/history/processImg")
    public String getProcessImg(@ApiParam("流程实例id") @RequestParam("processInstanceId") String processInstanceId) {
        return processHistoryService.getProcessImage(processInstanceId);
    }

}
