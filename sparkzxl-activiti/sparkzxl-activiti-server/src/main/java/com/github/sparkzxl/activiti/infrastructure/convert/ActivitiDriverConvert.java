package com.github.sparkzxl.activiti.infrastructure.convert;

import com.github.sparkzxl.activiti.domain.model.DriveProcess;
import com.github.sparkzxl.activiti.dto.DriverProcessParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: activiti驱动convert
 *
 * @author: zhouxinlei
 * @date: 2020-10-01 22:10:00
 */
@Mapper
public interface ActivitiDriverConvert {

    ActivitiDriverConvert INSTANCE = Mappers.getMapper(ActivitiDriverConvert.class);

    /**
     * 转化driverProcessDTO为DriveProcess
     *
     * @param driverProcessParam 流程驱动入参
     * @return DriveProcess
     */
    DriveProcess convertDriveProcess(DriverProcessParam driverProcessParam);
}
