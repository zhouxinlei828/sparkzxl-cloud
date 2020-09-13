package com.github.sparkzxl.activiti.interfaces.controller.process;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.application.service.process.IProcessDetailService;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessDetail;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailPageDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/processDetail/{modelId}")
    @ApiOperation("查询流程节点信息")
    public List<ProcessDetailDTO> getProcessDetail(@ApiParam("模型id") @PathVariable("modelId") String modelId) {
        return processDetailService.getProcessDetail(modelId);
    }

    @GetMapping("/processes")
    @ApiOperation("分页查询流程列表")
    public PageInfo<ProcessDetail> getProcessDetailList(ProcessDetailPageDTO processDetailPageDTO) {
        return processDetailService.getProcessDetailList(processDetailPageDTO);
    }

}
