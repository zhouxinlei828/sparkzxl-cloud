package com.sparksys.activiti.infrastructure.strategy;

import com.google.common.collect.Maps;
import com.sparksys.activiti.application.service.act.IProcessRepositoryService;
import com.sparksys.activiti.application.service.act.IProcessRuntimeService;
import com.sparksys.activiti.domain.service.act.ActWorkApiService;
import com.sparksys.activiti.infrastructure.constant.WorkflowConstants;
import com.sparksys.activiti.domain.entity.DriveProcess;
import com.sparksys.core.support.ResponseResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @Autowired
    private IProcessRepositoryService processRepositoryService;


    @Override
    public boolean slove(DriveProcess driveProcess) {
        String businessId = driveProcess.getBusinessId();
        String userId = driveProcess.getApplyUserId();
        ResponseResultStatus.FAILURE.assertNotTrue(processRuntimeService.getProcessInstanceByBusinessId(businessId) == null);
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("assignee", userId);
        variables.put("actType", driveProcess.getActType());
        identityService.setAuthenticatedUserId(String.valueOf(userId));
        ProcessInstance processInstance = processRuntimeService.startProcessInstanceByKey(driveProcess.getProcessDefinitionKey(),
                driveProcess.getBusinessId(),
                variables);
        String processInstanceId = processInstance.getProcessInstanceId();
        String deploymentId = processInstance.getDeploymentId();
        Model model = processRepositoryService.getModelByDeploymentId(deploymentId);
        log.info("启动activiti流程------++++++ProcessInstanceId：{}------++++++", processInstanceId);
        variables.put("actType", WorkflowConstants.WorkflowAction.SUBMIT);
        String comment = "开始节点跳过";
        return actWorkApiService.promoteProcess(userId, processInstanceId, model.getId(), WorkflowConstants.WorkflowAction.SUBMIT, comment
                , variables);
    }

    @Override
    public Integer[] supports() {
        return new Integer[]{WorkflowConstants.WorkflowAction.START};
    }
}
