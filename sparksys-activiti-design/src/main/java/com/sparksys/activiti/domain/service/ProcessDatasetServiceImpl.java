package com.sparksys.activiti.domain.service;

import com.sparksys.activiti.infrastructure.entity.ProcessDataset;
import com.sparksys.activiti.infrastructure.mapper.ProcessDatasetMapper;
import com.sparksys.activiti.application.service.IProcessDatasetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据集 服务实现类
 * </p>
 *
 * @author zhouxinlei
 * @since 2020-07-16
 */
@Service
public class ProcessDatasetServiceImpl extends ServiceImpl<ProcessDatasetMapper, ProcessDataset> implements IProcessDatasetService {

}
