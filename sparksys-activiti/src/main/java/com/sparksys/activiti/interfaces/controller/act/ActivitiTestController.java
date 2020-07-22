package com.sparksys.activiti.interfaces.controller.act;

import com.sparksys.activiti.application.service.driver.IActivitiDriverService;
import com.sparksys.activiti.application.service.process.IProcessRepositoryService;
import com.sparksys.activiti.interfaces.dto.DriveProcessDTO;
import com.sparksys.core.entity.AuthUserInfo;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * description: 流程驱动管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:29:54
 */
@RestController
@RequestMapping("/activiti/test/")
@ResponseResult
@WebLog
@Api(tags = "流程查询管理")
public class ActivitiTestController {

    private final IProcessRepositoryService repositoryService;

    public ActivitiTestController(IProcessRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @ApiOperation(value = "根据流程定义key获取相关流程定义对象列表")
    @GetMapping("applyDataSet")
    public List<ProcessDefinition> driveProcess(String processDefinitionKey) {
        return repositoryService.getProcessDefinitionsByKey(processDefinitionKey);
    }

}
