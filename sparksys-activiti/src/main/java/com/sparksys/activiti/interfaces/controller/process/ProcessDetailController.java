package com.sparksys.activiti.interfaces.controller.process;


import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.application.service.process.IProcessDetailService;
import com.sparksys.activiti.infrastructure.entity.ProcessDetail;
import com.sparksys.activiti.interfaces.dto.process.ProcessDetailDTO;
import com.sparksys.activiti.interfaces.dto.process.ProcessDetailPageDTO;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 流程节点管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 14:53:25
 */
@RestController
@ResponseResult
@WebLog
@RequestMapping("/process/detail")
@Api(tags = "流程节点管理")
public class ProcessDetailController {

    private final IProcessDetailService processDetailService;


    public ProcessDetailController(IProcessDetailService processDetailService) {
        this.processDetailService = processDetailService;
    }

    @GetMapping("/processDetail")
    @ApiOperation("查询流程节点信息")
    public List<ProcessDetailDTO> getProcessDetail(@ApiParam("模型id") String modelId) {
        return processDetailService.getProcessDetail(modelId);
    }

    @GetMapping("/processes")
    @ApiOperation("分页查询流程列表")
    public PageInfo<ProcessDetail> getProcessDetailList(ProcessDetailPageDTO processDetailPageDTO) {
        return processDetailService.getProcessDetailList(processDetailPageDTO);
    }

}
