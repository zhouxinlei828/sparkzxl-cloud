package com.github.sparkzxl.activiti.interfaces.controller.process;

import com.github.sparkzxl.activiti.application.service.process.IProcessTaskRuleService;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskRule;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessActionDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.TaskRuleSaveDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
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
public class ProcessTaskRuleController {

    private final IProcessTaskRuleService processTaskRuleService;

    public ProcessTaskRuleController(IProcessTaskRuleService processTaskRuleService) {
        this.processTaskRuleService = processTaskRuleService;
    }

    @PostMapping("processTaskRule")
    @ApiOperation("保存流程跳转规则")
    public boolean saveProcessTaskRule(@RequestBody @Validated TaskRuleSaveDTO taskRuleSaveDTO) {
        return processTaskRuleService.saveProcessTaskRule(taskRuleSaveDTO);
    }

    @GetMapping("processTaskRule/{processId}/{taskDefKey}")
    @ApiOperation("查询流程跳转规则")
    public List<ProcessTaskRule> getProcessTaskRule(@PathVariable("processId") @ApiParam("流程定义key") String processId,
                                                    @PathVariable("taskDefKey") @ApiParam("任务定义key") String taskDefKey) {
        return processTaskRuleService.getProcessTaskRule(processId, taskDefKey);
    }

    @GetMapping("action")
    @ApiOperation("查询流程动作类型")
    public List<ProcessActionDTO> getProcessAction() {
        return processTaskRuleService.getProcessAction();
    }

    @DeleteMapping("processTaskRule/{id}")
    @ApiOperation("删除流程跳转规则")
    public boolean deleteProcessTaskRule(@PathVariable("id") @ApiParam("主键") Long id) {
        return processTaskRuleService.removeById(id);
    }

}
