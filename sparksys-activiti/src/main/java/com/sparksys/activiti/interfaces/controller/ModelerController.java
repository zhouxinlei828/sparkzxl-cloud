package com.sparksys.activiti.interfaces.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sparksys.activiti.application.service.IModelerService;
import com.sparksys.log.annotation.WebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description:流程控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:46:24
 */
@Controller
@WebLog
@Slf4j
@Api(tags = "流程控制器管理")
public class ModelerController {


    private final IModelerService modelerService;

    public ModelerController(IModelerService modelerService) {
        this.modelerService = modelerService;
    }

    @Autowired
    private RepositoryService repositoryService;

    @ApiOperation("创建模型")
    @RequestMapping("/create")
    public void create(HttpServletResponse response, @ApiParam("模型名称") String name, @ApiParam("模型key") String key) throws IOException {
        String modelId = modelerService.createModel(name, key);
        response.sendRedirect("/editor?modelId=" + modelId);
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
