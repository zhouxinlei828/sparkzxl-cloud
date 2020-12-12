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
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @Override
    public DriverResult slove(DriveProcess driveProcess) {
        boolean lock = false;
        String businessId = driveProcess.getBusinessId();
        DriverResult driverResult = new DriverResult();
        try {
            lock = redisDistributedLock.lock(businessId, 0, 15);
            String userId = driveProcess.getUserId();
            int actType = driveProcess.getActType();
            if (lock) {
                ProcessInstance processInstance = processRuntimeService.getProcessInstanceByBusinessId(businessId);
                if (ObjectUtils.isEmpty(processInstance)) {
                    SparkZxlExceptionAssert.businessFail("流程实例为空，请检查参数是否正确");
                }
                String processDefinitionKey = processInstance.getProcessDefinitionKey();
                String processInstanceId = processInstance.getProcessInstanceId();
                DriverData driverData = DriverData.builder()
                        .userId(userId)
                        .processInstanceId(processInstanceId)
                        .processDefinitionKey(processDefinitionKey)
                        .businessId(businessId)
                        .comment(driveProcess.getComment())
                        .actType(actType)
                        .build();
                driverResult = actWorkApiService.jumpProcess(driverData);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return new Integer[]{WorkflowConstants.WorkflowAction.ROLLBACK, WorkflowConstants.WorkflowAction.JUMP};
    }

}
