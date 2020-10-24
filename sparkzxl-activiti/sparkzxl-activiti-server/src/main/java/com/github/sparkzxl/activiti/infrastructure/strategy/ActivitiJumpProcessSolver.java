package com.github.sparkzxl.activiti.infrastructure.strategy;

import com.github.sparkzxl.activiti.application.service.act.IProcessRuntimeService;
import com.github.sparkzxl.activiti.domain.entity.DriveProcess;
import com.github.sparkzxl.activiti.domain.service.act.ActWorkApiService;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: 推动activiti流程
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:28:09
 */
@Component
@Slf4j
public class ActivitiJumpProcessSolver extends AbstractActivitiSolver {

    @Autowired
    private IProcessRuntimeService processRuntimeService;
    @Autowired
    private ActWorkApiService actWorkApiService;

    @Override
    public DriverResult slove(DriveProcess driveProcess) {
        String businessId = driveProcess.getBusinessId();
        String userId = driveProcess.getUserId();
        int actType = driveProcess.getActType();
        ProcessInstance processInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        String processInstanceId = processInstance.getProcessInstanceId();
        return actWorkApiService.jumpProcess(
                userId,
                processInstanceId,
                processDefinitionKey,
                businessId,
                driveProcess.getComment(),
                actType);
    }

    @Override
    public Integer[] supports() {
        return new Integer[]{WorkflowConstants.WorkflowAction.ROLLBACK, WorkflowConstants.WorkflowAction.JUMP};
    }

}
