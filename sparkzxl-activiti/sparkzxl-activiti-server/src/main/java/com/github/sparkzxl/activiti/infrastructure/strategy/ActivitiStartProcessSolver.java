package com.github.sparkzxl.activiti.infrastructure.strategy;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.sparkzxl.activiti.application.service.act.IProcessRuntimeService;
import com.github.sparkzxl.activiti.domain.model.DriveProcess;
import com.github.sparkzxl.activiti.domain.model.DriverData;
import com.github.sparkzxl.activiti.domain.service.act.ActWorkApiService;
import com.github.sparkzxl.activiti.dto.DriverResult;
import com.github.sparkzxl.activiti.infrastructure.constant.WorkflowConstants;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.redisson.lock.RedisDistributedLock;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    private RedisDistributedLock redisDistributedLock;

    @Override
    public DriverResult slove(DriveProcess driveProcess) {
        boolean lock = false;
        String businessId = driveProcess.getBusinessId();
        boolean serviceInvocation = driveProcess.isServiceInvocation();
        DriverResult driverResult = new DriverResult();
        try {
            lock = redisDistributedLock.lock(businessId, 0, 15);
            if (lock) {
                String userId = driveProcess.getUserId();
                //查询是否存在已有流程，如果有，则不能进行启动工作流操作
                ProcessInstance originalProcessInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
                if (ObjectUtils.isNotEmpty(originalProcessInstance)) {
                    SparkZxlExceptionAssert.businessFail("流程已存在，请勿重复启动");
                }
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
                    DriverData driverData = DriverData.builder()
                            .userId(userId)
                            .processInstanceId(processInstanceId)
                            .processDefinitionKey(processInstance.getProcessDefinitionKey())
                            .businessId(businessId)
                            .serviceInvocation(serviceInvocation)
                            .comment(comment)
                            .actType(WorkflowConstants.WorkflowAction.JUMP)
                            .build();
                    driverResult = actWorkApiService.jumpProcess(driverData);
                } else {
                    variables.put("actType", WorkflowConstants.WorkflowAction.SUBMIT);
                    DriverData driverData = DriverData.builder()
                            .userId(userId)
                            .processInstanceId(processInstanceId)
                            .businessId(businessId)
                            .processDefinitionKey(processInstance.getProcessDefinitionKey())
                            .actType(WorkflowConstants.WorkflowAction.SUBMIT)
                            .comment(comment)
                            .variables(variables)
                            .build();
                    driverResult = actWorkApiService.promoteProcess(driverData);
                }
            } else {
                log.error("businessId = {},操作过于频繁，稍后再试！", businessId);
                if (serviceInvocation) {
                    driverResult.setErrorMsg("操作过于频繁，稍后再试！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            driverResult.setErrorMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("发生异常 Exception：{}", ExceptionUtil.getMessage(e));
        } finally {
            if (lock) {
                redisDistributedLock.releaseLock(businessId);
            }
        }
        return driverResult;
    }

    @Override
    public Integer[] supports() {
        return new Integer[]{WorkflowConstants.WorkflowAction.START};
    }
}
