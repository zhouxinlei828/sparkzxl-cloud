package com.sparksys.activiti.domain.service.process;

import com.sparksys.activiti.domain.repository.IProcessTaskStatusRepository;
import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.convert.ProcessInstanceConvert;
import com.sparksys.activiti.infrastructure.entity.ProcessInstance;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskStatus;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskStatusMapper;
import com.sparksys.activiti.application.service.process.IProcessTaskStatusService;
import com.sparksys.activiti.interfaces.dto.act.ProcessInstanceDTO;
import com.sparksys.core.utils.DateUtils;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 流程历史状态记录 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:57
 */
@Service
public class ProcessTaskStatusServiceImpl extends AbstractSuperCacheServiceImpl<ProcessTaskStatusMapper, ProcessTaskStatus> implements IProcessTaskStatusService {

    private final IProcessTaskStatusRepository taskStatusRepository;

    public ProcessTaskStatusServiceImpl(IProcessTaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public ProcessTaskStatus getProcessTaskStatus(String processInstanceId) {
        return taskStatusRepository.getProcessTaskStatus(processInstanceId);
    }

    @Override
    public List<ProcessInstanceDTO> getProcessInstanceList(String name) {
        List<ProcessInstance> processInstances = taskStatusRepository.getProcessInstanceList(name);
        return processInstances.stream().map(item -> {
            ProcessInstanceDTO processInstanceDTO = ProcessInstanceConvert.INSTANCE.convertProcessInstanceDTO(item);
            processInstanceDTO.setDueTime(DateUtils.getDatePoor(item.getDuration()));
            return processInstanceDTO;
        }).collect(Collectors.toList());
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}
