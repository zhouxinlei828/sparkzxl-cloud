package com.sparksys.activiti.interfaces.controller.model;

import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.application.service.act.IActReModelService;
import com.sparksys.activiti.application.service.model.IModelerService;
import com.sparksys.activiti.infrastructure.entity.ActReModel;
import com.sparksys.activiti.interfaces.dto.act.ModelPageDTO;
import com.sparksys.activiti.interfaces.dto.model.ModelSaveDTO;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * description:流程控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:46:24
 */
@RestController
@RequestMapping("/modeler")
@ResponseResult
@WebLog
@Slf4j
@Api(tags = "流程模型管理")
public class ModelerController {

    private final IModelerService modelerService;
    private final IActReModelService actReModelService;

    public ModelerController(IModelerService modelerService, IActReModelService actReModelService) {
        this.modelerService = modelerService;
        this.actReModelService = actReModelService;
    }

    @ApiOperation("查询流程模型列表")
    @GetMapping("model/list")
    @ResponseResult
    public PageInfo<ActReModel> modelList(ModelPageDTO modelPageDTO) {
        return actReModelService.actReModelList(modelPageDTO);
    }

    @ApiOperation("创建模型")
    @PostMapping("/model")
    public String create(@RequestBody @Valid ModelSaveDTO modelSaveDTO) {
        return modelerService.createModel(modelSaveDTO.getName(), modelSaveDTO.getKey());
    }

    @ApiOperation("发布流程")
    @PatchMapping("/publish/{modelId}")
    public boolean publish(@ApiParam("模型ID") @PathVariable("modelId") String modelId) {
        return modelerService.publishProcess(modelId);
    }

    @ApiOperation("撤销流程定义")
    @DeleteMapping("/revokePublish/{modelId}")
    public Object revokePublish(@ApiParam("模型ID") @PathVariable("modelId") String modelId) {
        return modelerService.revokePublish(modelId);
    }

    @ApiOperation("删除流程")
    @DeleteMapping("/processInstance/{modelId}")
    public Object deleteProcessInstance(@ApiParam("模型ID") @PathVariable("modelId") String modelId) {
        return modelerService.deleteProcessInstance(modelId);
    }

}
