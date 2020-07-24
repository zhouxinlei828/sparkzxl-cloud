package com.sparksys.activiti.infrastructure.strategy;

import com.google.common.collect.Maps;
import com.sparksys.activiti.application.service.act.IProcessRuntimeService;
import com.sparksys.activiti.domain.service.act.ActWorkApiService;
import com.sparksys.activiti.infrastructure.constant.WorkflowConstants;
import com.sparksys.activiti.domain.entity.DriveProcess;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description: 推动activiti流程
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:28:09
 */
@Component
@Slf4j
public class ActivitiSubmitProcessSolver extends AbstractActivitiSolver {

    @Autowired
    private IProcessRuntimeService processRuntimeService;
    @Autowired
    private ActWorkApiService actWorkApiService;

    @Override
    public boolean slove(DriveProcess driveProcess) {
        String businessId = driveProcess.getBusinessId();
        String applyUserId = driveProcess.getApplyUserId();
        String userId = driveProcess.getUserId();
        Map<String, Object> variables = Maps.newHashMap();
        if (StringUtils.isNotEmpty(applyUserId)) {
            variables.put("assignee", applyUserId);
        }
        variables.put("actType", driveProcess.getActType());
        ProcessInstance processInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
        return actWorkApiService.promoteProcess(userId, processInstance.getProcessInstanceId(), driveProcess.getActType(), driveProcess.getComment(), variables);
    }

    @Override
    public Integer[] supports() {
        return new Integer[]{WorkflowConstants.WorkflowAction.SUBMIT, WorkflowConstants.WorkflowAction.AGREE};
    }
}
