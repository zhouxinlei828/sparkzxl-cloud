package com.github.sparkzxl.activiti.api;

import com.github.sparkzxl.activiti.dto.ActivitiDataDTO;
import com.github.sparkzxl.activiti.dto.DriverProcessDTO;
import com.github.sparkzxl.activiti.dto.DriverResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: activiti驱动API接口
 *
 * @author: zhouxinlei
 * @date: 2020-10-01 19:42:13
 */
public interface ActivitiDriverApi {

    /**
     * 驱动业务流程
     *
     * @param driverProcessDTO 流程驱动入参
     * @return DriverResult
     */
    @ApiOperation("驱动业务流程")
    @PostMapping("/driverProcess")
    DriverResult driverProcess(@RequestBody @Validated DriverProcessDTO driverProcessDTO);

    /**
     * 查询业务activiti任务数据
     *
     * @param businessId           业务主键
     * @param processDefinitionKey 流程定义key
     * @return ActivitiDataDTO
     */
    @ApiOperation("查询业务activiti任务数据")
    @GetMapping("/activitiData")
    ActivitiDataDTO findActivitiData(@RequestParam("businessId") String businessId,
                                     @RequestParam("processDefinitionKey") String processDefinitionKey);

    /**
     * 查询业务activiti任务数据
     *
     * @param businessIds          业务主键
     * @param processDefinitionKey 流程定义key
     * @return ActivitiDataDTO
     */
    @ApiOperation("查询业务activiti任务数据")
    @GetMapping("/activitiDataList")
    List<ActivitiDataDTO> findActivitiDataList(@RequestParam("businessIds") List<String> businessIds,
                                               @RequestParam("processDefinitionKey") String processDefinitionKey);

    /**
     * 挂起流程
     *
     * @param businessId 业务主键
     * @return boolean
     */
    @ApiOperation("挂起流程")
    @DeleteMapping("/suspendProcess")
    boolean suspendProcess(@RequestParam("businessId") String businessId);

    /**
     * 删除流程
     *
     * @param businessId   业务主键
     * @param deleteReason 删除原因
     * @return boolean
     */
    @ApiOperation("删除流程实例")
    @DeleteMapping("/deleteProcessInstance")
    boolean deleteProcessInstance(@RequestParam("businessId") String businessId,
                                  @RequestParam("deleteReason") String deleteReason);
}
