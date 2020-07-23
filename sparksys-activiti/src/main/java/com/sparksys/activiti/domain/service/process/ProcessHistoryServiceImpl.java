package com.sparksys.activiti.domain.service.process;

import com.google.common.collect.Lists;
import com.sparksys.activiti.application.service.process.*;
import com.sparksys.activiti.infrastructure.constant.WorkflowConstants;
import com.sparksys.activiti.infrastructure.diagram.CustomProcessDiagramGeneratorImpl;
import com.sparksys.activiti.infrastructure.entity.ActHiTaskStatus;
import com.sparksys.activiti.infrastructure.entity.ProcessHistory;
import com.sparksys.activiti.infrastructure.enums.TaskStatusEnum;
import com.sparksys.activiti.infrastructure.utils.CloseableUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description: 历史流程 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 15:21:22
 */
@Service
@Slf4j
public class ProcessHistoryServiceImpl implements IProcessHistoryService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IProcessRepositoryService processRepositoryService;

    @Autowired
    private IProcessRuntimeService processRuntimeService;

    @Autowired
    private IActHiTaskStatusService actHiTaskStatusService;

    @Autowired
    private CustomProcessDiagramGeneratorImpl processDiagramGenerator;

    @Override
    public HistoricProcessInstance getHistoricProcessInstance(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public List<HistoricTaskInstance> getHistoricTasksByAssigneeId(String assignee) {
        return historyService.createHistoricTaskInstanceQuery().taskAssignee(assignee).list();
    }

    @Override
    public List<HistoricTaskInstance> getHistoricTasksByProcessInstanceId(String processInstanceId) {
        return historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByTaskId().list();
    }

    @Override
    public List<ProcessHistory> getProcessHistory(String processInstanceId) {
        List<ActHiTaskStatus> actHiTaskStatusList = actHiTaskStatusService.getProcessHistory(processInstanceId);
        List<HistoricActivityInstance> historicActivityInstances = getHistoricActivityInstance(processInstanceId);
        List<HistoricTaskInstance> historicTaskInstances = getHistoricTasksByProcessInstanceId(processInstanceId);
        List<ProcessHistory> processHistories = Lists.newArrayList();
        List<HistoricActivityInstance> specialHistoricActivityInstances =
                historicActivityInstances.stream().filter(item -> "startEvent".equals(item.getActivityType()) || "endEvent".equals(item.getActivityType()))
                        .collect(Collectors.toList());
        specialHistoricActivityInstances.forEach(historicActivityInstance -> {
            ProcessHistory processHistory = new ProcessHistory();
            processHistory.setProcessInstanceId(processInstanceId);
            processHistory.setStartTime(historicActivityInstance.getStartTime());
            processHistory.setEndTime(historicActivityInstance.getEndTime());
            processHistory.setDuration(historicActivityInstance.getDurationInMillis());
            processHistory.setAssignee(historicActivityInstance.getAssignee());
            if ("startEvent".equals(historicActivityInstance.getActivityType())) {
                processHistory.setTaskStatus(TaskStatusEnum.START.getDesc());
                processHistory.setTaskName("启动流程");
            }
            if ("endEvent".equals(historicActivityInstance.getActivityType())) {
                processHistory.setTaskStatus(TaskStatusEnum.END.getDesc());
                processHistory.setTaskName("完成流程");
            }
            processHistories.add(processHistory);
        });
        historicTaskInstances.forEach(historicTaskInstance -> {
            ProcessHistory processHistory = new ProcessHistory();
            processHistory.setProcessInstanceId(processInstanceId);
            processHistory.setTaskName(historicTaskInstance.getName());
            processHistory.setStartTime(historicTaskInstance.getStartTime());
            processHistory.setEndTime(historicTaskInstance.getEndTime());
            processHistory.setDuration(historicTaskInstance.getDurationInMillis());
            processHistory.setAssignee(historicTaskInstance.getAssignee());
            processHistory.setDueDate(historicTaskInstance.getDueDate());
            processHistory.setTaskStatus(TaskStatusEnum.START.getDesc());
            Optional<ActHiTaskStatus> actHiTaskStatusOptional =
                    actHiTaskStatusList.stream().filter(item -> StringUtils.equals(historicTaskInstance.getTaskDefinitionKey(),
                            item.getTaskDefKey())).findFirst();
            actHiTaskStatusOptional.ifPresent(value -> processHistory.setTaskStatus(value.getTaskStatus()));
            // todo 新加评论表
        });
        return processHistories;
    }

    public List<HistoricActivityInstance> getHistoricActivityInstance(String processInstanceId) {
        return historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceId().asc().list();
    }

    @Override
    public void getProcessImage(String processInstanceId, HttpServletResponse response) {
        InputStream imageStream = null;
        ServletOutputStream outputStream = null;
        try {
            if (StringUtils.isBlank(processInstanceId)) {
                log.error("参数为空");
            }
            // 获取历史流程实例
            HistoricProcessInstance processInstance = getHistoricProcessInstance(processInstanceId);

            // 获取流程定义ID
            String processDefinitionId = processInstance.getProcessDefinitionId();

            // 获取流程定义信息
            BpmnModel bpmnModel = processRepositoryService.getBpmnModel(processDefinitionId);

            // 获取流程历史中已执行节点
            List<HistoricActivityInstance> historicActivityInstance = getHistoricActivityInstance(processInstanceId);

            // 高亮环节id集合
            List<String> highLightedActivitis = new ArrayList<>();
            for (HistoricActivityInstance tempActivity : historicActivityInstance) {
                String activityId = tempActivity.getActivityId();
                highLightedActivitis.add(activityId);
            }

            // 高亮线路id集合
            List<String> highLightedFlows = getHighLightedFlows(bpmnModel, historicActivityInstance);

            Set<String> currIds =
                    processRuntimeService.getExecutionByProcInsId(processInstanceId).stream().map(Execution::getActivityId).collect(Collectors.toSet());

            imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,
                    highLightedFlows, "宋体", "宋体", "宋体",
                    null, 1.0,
                    new Color[]{WorkflowConstants.COLOR_NORMAL, WorkflowConstants.COLOR_CURRENT}, currIds);

            // 输出流程图
            outputStream = response.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = imageStream.read(b, 0, b.length)) != -1) {
                outputStream.write(b, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException("获取流程图出错", e);
        } finally {
            CloseableUtils.close(outputStream, imageStream);
        }
    }

    private List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {
        // 24小时制
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 用以保存高亮的线flowId
        List<String> highFlows = new ArrayList<String>();

        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // 对历史流程节点进行遍历
            // 得到节点定义的详细信息
            FlowNode activityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());
            // 用以保存后续开始时间相同的节点
            List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();
            FlowNode sameActivityImpl1 = null;
            // 第一个节点
            HistoricActivityInstance activityInstance = historicActivityInstances.get(i);
            HistoricActivityInstance activityInstance1;

            for (int k = i + 1; k <= historicActivityInstances.size() - 1; k++) {
                // 后续第1个节点
                activityInstance1 = historicActivityInstances.get(k);

                // 都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
                if ("userTask".equals(activityInstance.getActivityType()) && "userTask".equals(activityInstance1.getActivityType()) &&
                        df.format(activityInstance.getStartTime()).equals(df.format(activityInstance1.getStartTime()))) {

                } else {
                    // 找到紧跟在后面的一个节点
                    sameActivityImpl1 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());
                    break;
                }
            }
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                // 后续第一个节点
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
                // 后续第二个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);

                // 如果第一个节点和第二个节点开始时间相同保存
                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))) {
                    FlowNode sameActivityImpl2 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            // 取出节点的所有出去的线
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows();

            // 对所有的线进行遍历
            for (SequenceFlow pvmTransition : pvmTransitions) {
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                FlowNode pvmActivityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(pvmTransition.getTargetRef());
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
