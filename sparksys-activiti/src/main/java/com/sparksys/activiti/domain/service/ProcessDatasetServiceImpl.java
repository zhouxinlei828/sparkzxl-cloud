package com.sparksys.activiti.domain.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.domain.repository.IProcessDatasetRepository;
import com.sparksys.activiti.infrastructure.constant.ActivitiCache;
import com.sparksys.activiti.infrastructure.entity.ActivitiTask;
import com.sparksys.activiti.infrastructure.entity.ProcessDataset;
import com.sparksys.activiti.infrastructure.mapper.ProcessDatasetMapper;
import com.sparksys.activiti.application.service.IProcessDatasetService;
import com.sparksys.database.dto.PageDTO;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.database.utils.PageInfoUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 数据集 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:38:10
 */
@Service
public class ProcessDatasetServiceImpl extends AbstractSuperCacheServiceImpl<ProcessDatasetMapper, ProcessDataset> implements IProcessDatasetService {

    private final IProcessDatasetRepository processDatasetRepository;

    public ProcessDatasetServiceImpl(IProcessDatasetRepository processDatasetRepository) {
        this.processDatasetRepository = processDatasetRepository;
    }

    @Override
    protected String getRegion() {
        return ActivitiCache.PROCESS_DATASET;
    }

    @Override
    public PageInfo<ActivitiTask> getUserTask(PageDTO pageDTO, String assignee) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<ActivitiTask> activitiTasks = processDatasetRepository.getTasksByAssignee(assignee);
        return PageInfoUtils.pageInfo(activitiTasks);
    }

}
