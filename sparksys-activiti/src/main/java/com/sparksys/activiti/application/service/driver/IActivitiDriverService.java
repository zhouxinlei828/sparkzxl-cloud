package com.sparksys.activiti.application.service.driver;

import com.sparksys.activiti.interfaces.dto.DriveProcessDTO;

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
     * @param userId
     * @param driveProcessDTO
     * @return
     */
    boolean driveProcess(Long userId, DriveProcessDTO driveProcessDTO);
}
