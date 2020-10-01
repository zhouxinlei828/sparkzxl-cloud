package com.github.sparkzxl.activiti.interfaces.controller.process;

import com.github.sparkzxl.activiti.application.service.act.IProcessHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * description: 历史流程前端控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:01:50
 */
@Slf4j
@RestController
@RequestMapping("/process")
@Api(tags = "历史流程管理")
public class ProcessHistoryController {

    private final IProcessHistoryService processHistoryService;

    public ProcessHistoryController(IProcessHistoryService processHistoryService) {
        this.processHistoryService = processHistoryService;
    }

    @ApiOperation("获取流程图并显示")
    @GetMapping("/history/processImg/{processInstanceId}")
    public void getProcessImg(@ApiParam("流程实例id") @PathVariable String processInstanceId, HttpServletResponse response) {
        processHistoryService.getProcessImage(processInstanceId, response);
    }

}
