package com.sparksys.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.activiti.domain.repository.IProcessDetailRepository;
import com.sparksys.activiti.infrastructure.entity.ProcessDetail;
import com.sparksys.activiti.infrastructure.mapper.ProcessDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * description:流程详细节点 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 15:43:03
 */
@Repository
public class ProcessDetailRepositoryImpl implements IProcessDetailRepository {

    @Autowired
    private ProcessDetailMapper processDetailMapper;

    @Override
    public List<ProcessDetail> getProcessDetailList(String processName) {
        QueryWrapper<ProcessDetail> detailQueryWrapper = new QueryWrapper<>();
        Optional<String> processNameOptional = Optional.ofNullable(processName);
        processNameOptional.ifPresent((value) -> detailQueryWrapper.eq("process_name", processName));
        detailQueryWrapper.groupBy("model_id");
        return processDetailMapper.selectList(detailQueryWrapper);
    }

    @Override
    public List<ProcessDetail> getProcessDetail(String modelId) {
        return processDetailMapper.selectList(new QueryWrapper<ProcessDetail>().lambda().eq(ProcessDetail::getModelId, modelId));
    }
}
