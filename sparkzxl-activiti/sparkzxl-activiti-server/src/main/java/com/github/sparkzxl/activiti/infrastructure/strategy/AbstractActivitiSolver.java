package com.github.sparkzxl.activiti.infrastructure.strategy;

import com.github.sparkzxl.activiti.domain.entity.DriveProcess;
import com.github.sparkzxl.activiti.dto.DriverResult;

/**
 * description: 抽象业务处理器
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:09:28
 */
public abstract class AbstractActivitiSolver {
    /**
     * 流程业务处理
     *
     * @param driveProcess 驱动model
     * @return DriverResult
     */
    public abstract DriverResult slove(DriveProcess driveProcess);

    /**
     * 流程类型支持
     *
     * @return Integer[]
     */
    public abstract Integer[] supports();
}
