package com.sparksys.activiti.infrastructure.strategy;

import com.sparksys.activiti.application.service.process.IProcessRuntimeService;
import com.sparksys.activiti.domain.service.ActWorkApiService;
import com.sparksys.activiti.infrastructure.constant.WorkflowConstants;
import com.sparksys.activiti.infrastructure.entity.model.DriveProcess;
import com.sparksys.core.support.ResponseResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 启动activiti流程
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:28:09
 */
@Component
@Slf4j
public class ActivitiStartProcessSolver extends AbstractActivitiSolver {

    @Autowired
    private IdentityService identityService;
    @Autowired
    private IProcessRuntimeService processRuntimeService;
    @Autowired
    private ActWorkApiService actWorkApiService;

    @Override
    public boolean slove(DriveProcess driveProcess) {
        String businessId = driveProcess.getBusinessId();
        String userId = driveProcess.getApplyUserId();
        ResponseResultStatus.FAILURE.assertNotTrue(processRuntimeService.getProcessInstanceByBusinessId(businessId) == null);
        Map<String, Object> variables = new HashMap<>(2);
        variables.put("applyUserId", userId);
        variables.put("actType", driveProcess.getActType());
        identityService.setAuthenticatedUserId(String.valueOf(userId));
        ProcessInstance processInstance = processRuntimeService.startProcessInstanceByKey(driveProcess.getBpmnId(),
                driveProcess.getBusinessId(),
                variables);
        String processInstanceId = processInstance.getProcessInstanceId();
        log.info("启动activiti流程------++++++ProcessInstanceId：{}------++++++", processInstanceId);
        return actWorkApiService.promoteProcess(userId, processInstanceId, driveProcess.getActType(), driveProcess.getComment(), variables);
    }

    @Override
    public Integer support() {
        return WorkflowConstants.WorkflowAction.START;
    }
}
