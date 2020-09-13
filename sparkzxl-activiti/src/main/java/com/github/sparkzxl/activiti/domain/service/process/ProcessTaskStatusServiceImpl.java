package com.github.sparkzxl.activiti.domain.service.process;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.application.service.process.IProcessTaskStatusService;
import com.github.sparkzxl.activiti.domain.repository.IProcessTaskStatusRepository;
import com.github.sparkzxl.activiti.infrastructure.constant.ActivitiCache;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessTaskStatus;
import com.github.sparkzxl.activiti.infrastructure.mapper.ProcessTaskStatusMapper;
import com.github.sparkzxl.activiti.interfaces.dto.act.InstancePageDTO;
import com.github.sparkzxl.core.utils.DateUtils;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageInfo<ProcessInstance> getProcessInstanceList(InstancePageDTO instancePageDTO) {
        PageInfo<ProcessInstance> processInstancePageInfo = taskStatusRepository.getProcessInstanceList(instancePageDTO.getPageNum(),
                instancePageDTO.getPageSize(), instancePageDTO.getName());
        List<ProcessInstance> processInstances = processInstancePageInfo.getList();
        processInstances.forEach(item -> item.setDueTime(DateUtils.formatBetween(item.getDuration())));
        processInstancePageInfo.setList(processInstances);
        return processInstancePageInfo;
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}
