package com.sparksys.activiti.application.listener;

import com.sparksys.activiti.infrastructure.constant.WorkflowConstants;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * description: 启动项目时是否部署流程
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:04:10
 */
@Component
@Slf4j
public class ApplicationInitializeListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 配置文件中控制启动项目时是否部署流程
     */
    @Value("${process.deploy}")
    private Boolean isDeploy;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        deploymentProcess();
    }

    /**
     * 部署默认流程
     */
    private void deploymentProcess() {
        // 根据流程名称查询默认流程是否已经部署
        List<Deployment> deployments = repositoryService.createDeploymentQuery().deploymentName(WorkflowConstants.DEFAULT_DS_PROCESS_NAME).list();
        // 如果还没部署，或配置文件配置为重新部署，则重新部署
        if (CollectionUtils.isEmpty(deployments) || isDeploy) {
            Deployment deployment = repositoryService.createDeployment()
                    .addClasspathResource(WorkflowConstants.DEFAULT_DS_PROCESS)
                    .name(WorkflowConstants.DEFAULT_DS_PROCESS_NAME).deploy();
            log.info("deploy default dataset process,deploy id=[{}],deploy name=[{}]", deployment.getId(), deployment.getName());
        }
    }
}
