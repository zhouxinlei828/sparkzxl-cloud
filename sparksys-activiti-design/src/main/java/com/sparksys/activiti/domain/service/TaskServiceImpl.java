package com.sparksys.activiti.domain.service;

import com.sparksys.activiti.dto.TaskStepDTO;
import com.sparksys.activiti.service.AbstractTaskService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskServiceImpl extends AbstractTaskService {
    @Override
    public String getProcessInstanceIdByBusinessKey(Long aLong) {
        return null;
    }

    @Override
    public String getTaskIdByProcessInstanceId(String s) {
        return null;
    }

    @Override
    public void insertTaskStep(TaskStepDTO taskStepDTO) {

    }

    @Override
    public String findActHiTaskForAssignee(Map<String, String> map) {
        return null;
    }

    @Override
    public String findCurrentTaskId(String s) {
        return null;
    }
}
