package com.sparksys.activiti.domain.service.process;

import com.sparksys.activiti.application.service.process.IProcessRepositoryService;
import com.sparksys.activiti.application.service.process.IProcessTaskService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description: 流程定义相关 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 15:43:03
*/
@Service
@Slf4j
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
public class ProcessRepositoryServiceImpl implements IProcessRepositoryService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IProcessTaskService processTaskService;

    @Override
    public List<Deployment> deployList() {
        return repositoryService.createDeploymentQuery().list();
    }

    @Override
    public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
        Task task = processTaskService.getTaskByTaskId(taskId);
        return repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
    }

    @Override
    public List<ProcessDefinition> getProcessDefinitionsByKey(String processDefinitionKey) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
                .orderByProcessDefinitionVersion().desc().list();
    }

    @Override
    public ProcessDefinition getProcessDefinitionByKey(String processDefinitionKey) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
                .orderByDeploymentId().desc().list().get(0);
    }

    @Override
    public ProcessDefinition getProcessDefinitionById(String processDefinitionId) {
        return repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
    }

    @Override
    public ProcessDefinitionEntity getProcessDefinitionEntity(String processDefinitionId) {
        return (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processDefinitionId);
    }

    @Override
    public Model newModel() {
        return repositoryService.newModel();
    }

    @Override
    @Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
    public void saveModel(Model model) {
        repositoryService.saveModel(model);
    }

    @Override
    public void addModelEditorSource(String modelId, byte[] bytes) {
        repositoryService.addModelEditorSource(modelId, bytes);
    }

    @Override
    public List<Model> getModelsOrderByCreateTime() {
        return repositoryService.createModelQuery().orderByCreateTime().desc().list();
    }

    @Override
    public void deployModel(String modelName, String processName, byte[] bpmnBytes) {
        repositoryService.createDeployment().name(modelName).addString(processName, new String(bpmnBytes)).deploy();
    }

    @Override
    public Model getModelByModelId(String modelId) {
        return repositoryService.getModel(modelId);
    }

    @Override
    public List<Model> getModelList() {
        return repositoryService.createModelQuery().list();
    }

    @Override
    public byte[] getModelEditorSourceByModelId(String modelId) {
        return repositoryService.getModelEditorSource(modelId);
    }

    @Override
    public BpmnModel getBpmnModel(String processDefinitionId) {
        return repositoryService.getBpmnModel(processDefinitionId);
    }
}
