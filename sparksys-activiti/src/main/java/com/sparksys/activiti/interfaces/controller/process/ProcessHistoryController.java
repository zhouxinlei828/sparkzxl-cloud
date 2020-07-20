package com.sparksys.activiti.interfaces.controller.process;

import com.sparksys.activiti.application.service.process.IProcessHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * description: 历史流程前端控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:01:50
 */
@Slf4j
@RestController
@RequestMapping("/process/history")
@Api(tags = "历史流程管理")
public class ProcessHistoryController {

    @Autowired
    private IProcessHistoryService processHistoryService;

    @ApiOperation("获取流程图并显示")
    @GetMapping("/getProcessImg/{processInstanceId}")
    public void getProcessImg(@ApiParam("流程定义id") @PathVariable String processInstanceId, HttpServletResponse response) {
        processHistoryService.getProcessImage(processInstanceId, response);
    }

}
