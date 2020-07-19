package com.sparksys.activiti.infrastructure.repository;

import com.sparksys.activiti.domain.repository.IProcessDatasetRepository;
import com.sparksys.activiti.infrastructure.entity.ActivitiTask;
import com.sparksys.activiti.infrastructure.mapper.ProcessDatasetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 数据集仓储 实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:14:45
 */
@Repository
public class ProcessDatasetRepositoryImpl implements IProcessDatasetRepository {

    @Autowired
    private ProcessDatasetMapper processDatasetMapper;

    @Override
    public List<ActivitiTask> getTasksByAssignee(String assignee) {
        return processDatasetMapper.getTasksByAssignee(assignee);
    }
}
