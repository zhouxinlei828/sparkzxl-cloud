package com.github.sparkzxl.activiti.infrastructure.strategy;

import com.github.sparkzxl.activiti.application.service.act.IProcessRuntimeService;
import com.github.sparkzxl.activiti.domain.entity.DriveProcess;
import com.github.sparkzxl.activiti.domain.service.act.ActWorkApiService;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import com.github.sparkzxl.core.support.ResponseResultStatus;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public DriverResult slove(DriveProcess driveProcess) {
        String businessId = driveProcess.getBusinessId();
        String userId = driveProcess.getUserId();
        ResponseResultStatus.FAILURE.assertNotTrue(processRuntimeService.getProcessInstanceByBusinessId(businessId) == null);
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("assignee", driveProcess.getApplyUserId());
        variables.put("actType", driveProcess.getActType());
        identityService.setAuthenticatedUserId(String.valueOf(userId));
        ProcessInstance processInstance = processRuntimeService.startProcessInstanceByKey(driveProcess.getProcessDefinitionKey(),
                driveProcess.getBusinessId(),
                variables);
        String processInstanceId = processInstance.getProcessInstanceId();
        log.info("启动activiti流程------++++++ProcessInstanceId：{}------++++++", processInstanceId);
        String comment = driveProcess.getComment();
        if (StringUtils.isEmpty(comment)) {
            comment = "开始节点跳过";
        }
        boolean needJump = driveProcess.isNeedJump();
        if (needJump) {
            return actWorkApiService.jumpProcess(
                    userId,
                    processInstanceId,
                    processInstance.getProcessDefinitionKey(),
                    businessId,
                    comment,
                    WorkflowConstants.WorkflowAction.JUMP);
        } else {
            variables.put("actType", WorkflowConstants.WorkflowAction.SUBMIT);
            return actWorkApiService.promoteProcess(
                    userId,
                    processInstanceId,
                    businessId,
                    WorkflowConstants.WorkflowAction.SUBMIT,
                    comment,
                    variables);
        }
    }

    @Override
    public Integer[] supports() {
        return new Integer[]{WorkflowConstants.WorkflowAction.START};
    }
}
