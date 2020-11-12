package com.github.sparkzxl.activiti.domain.service.ext;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.application.service.ext.IExtProcessStatusService;
import com.github.sparkzxl.activiti.domain.repository.IExtProcessStatusRepository;
import com.github.sparkzxl.activiti.infrastructure.constant.ActivitiCache;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessStatus;
import com.github.sparkzxl.activiti.infrastructure.mapper.ExtProcessStatusMapper;
import com.github.sparkzxl.activiti.interfaces.dto.act.InstancePageDTO;
import com.github.sparkzxl.core.utils.DateUtils;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 流程历史状态记录 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:37:57
 */
@Service
public class ExtProcessStatusServiceImpl extends AbstractSuperCacheServiceImpl<ExtProcessStatusMapper, ExtProcessStatus> implements IExtProcessStatusService {

    private final IExtProcessStatusRepository extProcessStatusRepository;

    public ExtProcessStatusServiceImpl(IExtProcessStatusRepository extProcessStatusRepository) {
        this.extProcessStatusRepository = extProcessStatusRepository;
    }

    @Override
    public List<ExtProcessStatus> getExtProcessStatusList(String businessId) {
        return extProcessStatusRepository.getExtProcessStatusList(businessId);
    }

    @Override
    public ExtProcessStatus getExtProcessStatus(String businessId, String processInstanceId) {
        return extProcessStatusRepository.getExtProcessStatus(businessId, processInstanceId);
    }

    @Override
    public PageInfo<ProcessInstance> getProcessInstanceList(InstancePageDTO instancePageDTO) {
        PageInfo<ProcessInstance> processInstancePageInfo = extProcessStatusRepository.getProcessInstanceList(instancePageDTO.getPageNum(),
                instancePageDTO.getPageSize(), instancePageDTO.getName());
        List<ProcessInstance> processInstances = processInstancePageInfo.getList();
        processInstances.forEach(item -> {
            if (ObjectUtils.isNotEmpty(item.getDuration())) {
                item.setDueTime(DateUtils.formatBetween(item.getDuration()));
            }
        });
        processInstancePageInfo.setList(processInstances);
        return processInstancePageInfo;
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.ACT_TASK_STEP;
    }
}
