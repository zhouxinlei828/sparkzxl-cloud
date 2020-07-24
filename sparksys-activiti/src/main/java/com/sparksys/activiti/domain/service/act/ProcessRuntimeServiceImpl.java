package com.sparksys.activiti.domain.service.act;

import com.sparksys.activiti.application.service.act.IProcessRuntimeService;
import com.sparksys.activiti.application.service.act.IProcessTaskService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
public class ProcessRuntimeServiceImpl implements IProcessRuntimeService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IProcessTaskService processTaskService;

    @Override
    public ProcessInstance startProcessInstanceByKey(String definitionKey) {
        return runtimeService.startProcessInstanceByKey(definitionKey);
    }

    @Override
    public ProcessInstance startProcessInstanceByKey(String bpmnId, String businessId, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(bpmnId, businessId, variables);
    }

    @Override
    public ProcessInstance startProcessInstanceByProDefId(String processDefinitionId) {
        return runtimeService.startProcessInstanceById(processDefinitionId);
    }

    @Override
    public ProcessInstance getProcessInstance(String processInstanceId) {
        return runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public ProcessInstance getProcessInstanceByBusinessId(String businessId) {
        return runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessId).singleResult();
    }

    @Override
    public List<String> getActiveActivityIds(String executionId) {
        return runtimeService.getActiveActivityIds(executionId);
    }

    @Override
    public ExecutionEntity getExecutionEntityByExecutionId(String executionId) {
        return (ExecutionEntity) runtimeService.createExecutionQuery().executionId(executionId).singleResult();
    }

    @Override
    public ProcessInstance getProcessInstanceByTaskId(String taskId) {
        Task task = processTaskService.getTaskByTaskId(taskId);
        return getProcessInstance(task.getProcessInstanceId());
    }

    @Override
    public Object getVariable(String executionId, String variableName) {
        return runtimeService.getVariable(executionId, variableName);
    }

    @Override
    public Map<String, Object> getVariables(String executionId) {
        return runtimeService.getVariables(executionId);
    }

    @Override
    public List<Execution> getExecutionByProcInsId(String processInstanceId) {
        return runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
    }

    @Override
    public List<Execution> getExecutionEntityByProDefKey(String processDefinitionKey) {
        return runtimeService.createExecutionQuery().processDefinitionKey(processDefinitionKey)
                .orderByProcessInstanceId().desc().list();
    }

    @Override
    public boolean processIsEnd(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        return processInstance == null;
    }

    @Override
    public void suspendProcess(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
    }

}
