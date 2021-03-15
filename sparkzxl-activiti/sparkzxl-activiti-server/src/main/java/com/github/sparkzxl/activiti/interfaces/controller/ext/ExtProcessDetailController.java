package com.github.sparkzxl.activiti.interfaces.controller.ext;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.application.service.ext.IExtProcessDetailService;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessDetail;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailPageDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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
public class ExtProcessDetailController {

    private final IExtProcessDetailService processDetailService;

    public ExtProcessDetailController(IExtProcessDetailService processDetailService) {
        this.processDetailService = processDetailService;
    }

    @GetMapping("/list")
    @ApiOperation("查询流程节点信息")
    public List<ProcessDetailDTO> getProcessDetail(@ApiParam("模型id") @RequestParam("modelId") String modelId) {
        return processDetailService.getProcessDetail(modelId);
    }

    @GetMapping("/processes")
    @ApiOperation("分页查询流程列表")
    public PageInfo<ExtProcessDetail> getProcessDetailList(ProcessDetailPageDTO processDetailPageDTO) {
        return processDetailService.getProcessDetailList(processDetailPageDTO);
    }

}
