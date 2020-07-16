package com.sparksys.activiti.domain.service;

import com.sparksys.activiti.infrastructure.entity.ProcessTask;
import com.sparksys.activiti.infrastructure.mapper.ProcessTaskMapper;
import com.sparksys.activiti.application.service.IProcessTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务处理记录 服务实现类
 * </p>
 *
 * @author zhouxinlei
 * @since 2020-07-16
 */
@Service
public class ProcessTaskServiceImpl extends ServiceImpl<ProcessTaskMapper, ProcessTask> implements IProcessTaskService {

}
