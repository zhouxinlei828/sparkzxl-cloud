package com.sparksys.activiti.interfaces.controller.act;

import cn.hutool.json.JSONUtil;
import com.sparksys.activiti.application.service.process.IProcessRepositoryService;
import com.sparksys.activiti.application.service.process.IProcessTaskService;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 流程驱动管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:29:54
 */
@RestController
@RequestMapping("/activiti/test/")
@WebLog
@Api(tags = "流程查询管理")
@Slf4j
public class ActivitiTestController {

    private final IProcessRepositoryService repositoryService;
    private final IProcessTaskService processTaskService;

    public ActivitiTestController(IProcessRepositoryService repositoryService, IProcessTaskService processTaskService) {
        this.repositoryService = repositoryService;
        this.processTaskService = processTaskService;
    }

    @ApiOperation(value = "根据流程定义key获取相关流程定义对象列表")
    @ResponseResult
    @GetMapping("processDefinitionsByKey")
    public List<ProcessDefinition> processDefinitionsByKey(String processDefinitionKey) {
        return repositoryService.getProcessDefinitionsByKey(processDefinitionKey);
    }

    @ApiOperation(value = "读取直接分配给当前人的任务")
    @GetMapping("getTasksByAssigneeAndBusKey")
    public void getTasksByAssigneeAndBusKey(String applyUserId, String businessId) {
        Task task = processTaskService.getTasksByAssigneeAndBusKey(applyUserId, businessId);
        log.info("当前人的任务为：{}", JSONUtil.toJsonPrettyStr(task));
    }

    @ApiOperation(value = "获取任务变量")
    @GetMapping("getTaskVariable")
    public Object getTaskVariable(String taskId, String key) {
        return processTaskService.getVariable(taskId, key);
    }

}
