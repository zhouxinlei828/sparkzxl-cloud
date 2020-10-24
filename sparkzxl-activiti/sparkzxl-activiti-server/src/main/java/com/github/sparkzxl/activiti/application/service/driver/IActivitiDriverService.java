package com.github.sparkzxl.activiti.application.service.driver;

import com.github.sparkzxl.activiti.dto.ActivitiDataDTO;
import com.github.sparkzxl.activiti.dto.DriverProcessDTO;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.dto.UserNextTask;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessNextTaskDTO;

import java.util.List;

/**
 * description: 流程驱动 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:26:54
 */
public interface IActivitiDriverService {

    /**
     * activiti流程驱动接口
     *
     * @param driverProcessDTO 流程驱动入参
     * @return DriverResult
     */
    DriverResult driverProcess(DriverProcessDTO driverProcessDTO);

    /**
     * 获取下一步任务详情
     *
     * @param processNextTaskDTO 查询下一步入参
     * @return List<UserNextTask>
     */
    List<UserNextTask> getNextUserTask(ProcessNextTaskDTO processNextTaskDTO);

    /**
     * 查询业务activiti任务数据
     *
     * @param businessId           业务主键
     * @param processDefinitionKey 流程定义key
     * @return ActivitiDataDTO
     */
    ActivitiDataDTO findActivitiData(String businessId, String processDefinitionKey);

    /**
     * 查询业务activiti任务数据
     *
     * @param businessIds          业务主键
     * @param processDefinitionKey 流程定义key
     * @return ActivitiDataDTO
     */
    List<ActivitiDataDTO> findActivitiDataList(List<String> businessIds, String processDefinitionKey);

    /**
     * 挂起流程
     *
     * @param businessId 业务主键
     * @return boolean
     */
    boolean suspendProcess(String businessId);

    /**
     * 删除流程实例
     * @param businessId 业务主键
     * @param deleteReason 删除原因
     * @return boolean
     */
    boolean deleteProcessInstance(String businessId, String deleteReason);
}
