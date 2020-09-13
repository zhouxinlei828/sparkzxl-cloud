package com.github.sparkzxl.activiti.interfaces.dto.process;

import lombok.Builder;
import lombok.Data;
import java.util.*;

/**
 * description: 用户任务详情
 *
 * @author: zhouxinlei
 * @date: 2020-07-22 18:14:38
 */
@Data
@Builder
public class UserNextTask {

    protected String assignee;
    protected String owner;
    protected String priority;
    protected String dueDate;
    protected String businessCalendarName;
    protected List<String> candidateUsers;
    protected List<String> candidateGroups;
    private String  taskDefKey;
    private String  taskName;
}
