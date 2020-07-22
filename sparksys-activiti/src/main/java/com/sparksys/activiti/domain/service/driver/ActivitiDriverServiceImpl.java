package com.sparksys.activiti.domain.service.driver;

import com.sparksys.activiti.application.service.driver.IActivitiDriverService;
import com.sparksys.activiti.infrastructure.entity.model.DriveProcess;
import com.sparksys.activiti.infrastructure.strategy.AbstractActivitiSolver;
import com.sparksys.activiti.infrastructure.strategy.ActivitiSolverChooser;
import com.sparksys.activiti.interfaces.dto.DriveProcessDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description: 流程驱动 服务 实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:27:58
 */
@Service
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
public class ActivitiDriverServiceImpl implements IActivitiDriverService {


    @Autowired
    private ActivitiSolverChooser activitiSolverChooser;

    @Override
    public boolean driveProcess(Long userId, DriveProcessDTO driveProcessDTO) {
        int actType = driveProcessDTO.getActType();
        AbstractActivitiSolver activitiSolver = activitiSolverChooser.chooser(actType);
        DriveProcess driveProcess = new DriveProcess();
        BeanUtils.copyProperties(driveProcessDTO, driveProcess);
        return activitiSolver.slove(driveProcess);
    }
}
