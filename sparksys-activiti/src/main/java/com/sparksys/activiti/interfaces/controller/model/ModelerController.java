package com.sparksys.activiti.interfaces.controller.model;

import com.sparksys.activiti.application.service.model.IModelerService;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * description:流程控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:46:24
 */
@RestController
@ResponseResult
@WebLog
@Slf4j
@Api(tags = "流程控制器管理")
public class ModelerController {


    private final IModelerService modelerService;

    public ModelerController(IModelerService modelerService) {
        this.modelerService = modelerService;
    }

    @ApiOperation("创建模型")
    @GetMapping("/create")
    public String create(@ApiParam("模型名称") String name,
                       @ApiParam("模型key") String key) {
        return modelerService.createModel(name, key);
    }

    @ApiOperation("发布流程")
    @GetMapping("/publish")
    public boolean publish(@ApiParam("模型ID") @RequestParam(value = "modelId") String modelId) {
        return modelerService.publishProcess(modelId);
    }

    @ApiOperation("撤销流程定义")
    @GetMapping("/revokePublish")
    public Object revokePublish(@ApiParam("模型ID") String modelId) {
        return modelerService.revokePublish(modelId);
    }

    @ApiOperation("删除流程实例")
    @GetMapping("/delete")
    public Object deleteProcessInstance(@ApiParam("模型ID") String modelId) {
        return modelerService.deleteProcessInstance(modelId);
    }

}
