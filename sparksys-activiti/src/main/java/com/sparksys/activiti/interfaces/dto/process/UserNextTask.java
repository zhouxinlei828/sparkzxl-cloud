package com.sparksys.activiti.interfaces.dto.process;

import lombok.Data;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.CustomProperty;
import org.activiti.bpmn.model.FormProperty;

import java.util.*;

/**
 * description: 用户任务详情
 *
 * @author: zhouxinlei
 * @date: 2020-07-22 18:14:38
 */
@Data
public class UserNextTask {

    protected String assignee;
    protected String owner;
    protected String priority;
    protected String formKey;
    protected String dueDate;
    protected String businessCalendarName;
    protected String category;
    protected String extensionId;
    protected List<String> candidateUsers;
    protected List<String> candidateGroups;
    protected List<FormProperty> formProperties;
    protected List<ActivitiListener> taskListeners;
    protected String skipExpression;
    protected Map<String, Set<String>> customUserIdentityLinks;
    protected Map<String, Set<String>> customGroupIdentityLinks;
    protected List<CustomProperty> customProperties;
}
