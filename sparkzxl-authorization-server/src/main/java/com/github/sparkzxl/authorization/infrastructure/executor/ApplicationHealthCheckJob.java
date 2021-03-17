package com.github.sparkzxl.authorization.infrastructure.executor;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.github.sparkzxl.authorization.application.service.IApplicationService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthApplication;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description:
 *
 * @author charles.zhou
 * @date 2021-03-17 10:48:18
 */
@Slf4j
@Component
public class ApplicationHealthCheckJob {

    private IApplicationService applicationService;

    @Autowired
    public void setApplicationService(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @XxlJob("applicationHealthCheck")
    public ReturnT<String> applicationHealthCheck(String param) {
        List<AuthApplication> authApplications = applicationService.applicationList();
        authApplications.forEach(authApplication -> {
            HttpResponse httpResponse = HttpRequest.get(authApplication.getHealthCheck()).execute();
            boolean responseOk = httpResponse.isOk();
            authApplication.setHealthStatus(responseOk);
        });
        applicationService.updateBatchById(authApplications);
        return ReturnT.SUCCESS;
    }
}
