package com.github.sparkzxl.activiti.interfaces.controller.ext;

import com.github.sparkzxl.activiti.application.service.ext.IExtProcessTaskRuleService;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessTaskRule;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessActionDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description:流程流向管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 15:44:59
 */
@RestController
@ResponseResult
@WebLog
@RequestMapping("/process/rule")
@Api(tags = "流程流向管理")
public class ExtProcessTaskRuleController {

    private final IExtProcessTaskRuleService processTaskRuleService;

    public ExtProcessTaskRuleController(IExtProcessTaskRuleService processTaskRuleService) {
        this.processTaskRuleService = processTaskRuleService;
    }

    @PostMapping("processTaskRule")
    @ApiOperation("保存流程跳转规则")
    public boolean saveProcessTaskRule(@RequestBody @Validated TaskRuleSaveDTO taskRuleSaveDTO) {
        return processTaskRuleService.saveProcessTaskRule(taskRuleSaveDTO);
    }

    @GetMapping("processTaskRule")
    @ApiOperation("查询流程跳转规则")
    public List<ExtProcessTaskRule> getProcessTaskRule(@RequestParam("processDefinitionKey") @ApiParam("流程定义key") String processDefinitionKey,
                                                       @RequestParam("taskDefKey") @ApiParam("任务定义key") String taskDefKey) {
        return processTaskRuleService.getProcessTaskRule(processDefinitionKey, taskDefKey);
    }

    @GetMapping("action")
    @ApiOperation("查询流程动作类型")
    public List<ProcessActionDTO> getProcessAction() {
        return processTaskRuleService.getProcessAction();
    }

    @DeleteMapping("processTaskRule")
    @ApiOperation("删除流程跳转规则")
    public boolean deleteProcessTaskRule(@RequestParam("id") @ApiParam("主键") Long id) {
        return processTaskRuleService.removeById(id);
    }

}
