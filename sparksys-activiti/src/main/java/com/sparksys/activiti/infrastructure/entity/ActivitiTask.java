package com.sparksys.activiti.infrastructure.entity;

import lombok.*;
import org.activiti.engine.task.Task;

/**
 * description: 前端展示任务信息
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:50:00
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivitiTask extends ProcessDataset {

    private String taskId;

    private String taskName;

    public ActivitiTask(Task task) {
        this.taskId = task.getId();
        this.taskName = task.getName();
        setProcInstId(task.getProcessInstanceId());
    }

}
