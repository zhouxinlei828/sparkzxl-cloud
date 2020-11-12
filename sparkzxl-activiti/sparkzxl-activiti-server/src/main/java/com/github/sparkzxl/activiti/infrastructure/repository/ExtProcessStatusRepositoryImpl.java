package com.github.sparkzxl.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.domain.repository.IExtProcessStatusRepository;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessStatus;
import com.github.sparkzxl.activiti.infrastructure.mapper.ExtProcessStatusMapper;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:流程状态 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-23 13:59:16
 */
@Repository
public class ExtProcessStatusRepositoryImpl implements IExtProcessStatusRepository {

    @Autowired
    private ExtProcessStatusMapper extProcessStatusMapper;

    @Override
    public List<ExtProcessStatus> getExtProcessStatusList(String businessId) {
        return extProcessStatusMapper.selectList(new LambdaQueryWrapper<ExtProcessStatus>()
                .eq(ExtProcessStatus::getBusinessId, businessId).orderByAsc(ExtProcessStatus::getCreateTime));
    }

    @Override
    public ExtProcessStatus getExtProcessStatus(String businessId, String processInstanceId) {
        return extProcessStatusMapper.selectOne(new LambdaQueryWrapper<ExtProcessStatus>()
                .eq(ExtProcessStatus::getBusinessId, businessId)
                .eq(ExtProcessStatus::getProcessInstanceId, processInstanceId).last("limit 1"));
    }

    @Override
    public PageInfo<ProcessInstance> getProcessInstanceList(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfoUtils.pageInfo(extProcessStatusMapper.getProcessInstanceList(name));
    }
}
