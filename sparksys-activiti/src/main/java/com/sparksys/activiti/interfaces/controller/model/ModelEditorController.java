package com.sparksys.activiti.interfaces.controller.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sparksys.activiti.application.service.model.IModelService;
import com.sparksys.activiti.application.service.act.IProcessRepositoryService;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.repository.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 获取model的节点信息，编辑器根据返回的json进行绘图
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:23:53
 */
@RestController
@RequestMapping("/service/")
@WebLog
@Slf4j
@Api(tags = "模型编辑器管理")
public class ModelEditorController implements ModelDataJsonConstants {

    private final IModelService modelEditorService;
    private final IProcessRepositoryService processRepositoryService;
    public ModelEditorController(IModelService modelEditorService, IProcessRepositoryService processRepositoryService) {
        this.modelEditorService = modelEditorService;
        this.processRepositoryService = processRepositoryService;
    }

    @ApiOperation("查询模型列表")
    @GetMapping("model/list")
    @ResponseResult
    public List<Model> modelList() {
        return processRepositoryService.getModelList();
    }

    @ApiOperation("获取流程json信息")
    @GetMapping("model/{modelId}/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        return modelEditorService.getEditorJson(modelId);
    }

    @ApiOperation("保存流程")
    @PutMapping("model/{modelId}/save")
    public boolean saveModel(@PathVariable String modelId, String name, String description
            , String json_xml, String svg_xml) {
        return modelEditorService.saveModel(modelId, name, description, json_xml, svg_xml);
    }

    @ApiOperation("获取流程json文件")
    @GetMapping("editor/stencilset")
    public String getProcessJson() {
        return modelEditorService.getProcessJson();
    }
}
