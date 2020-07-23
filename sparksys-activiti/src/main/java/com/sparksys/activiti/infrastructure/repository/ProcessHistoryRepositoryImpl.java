package com.sparksys.activiti.infrastructure.repository;

import com.sparksys.activiti.infrastructure.mapper.ProcessHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * description: 流程历史 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-23 18:03:38
 */
@Repository
public class ProcessHistoryRepositoryImpl implements IProcessHistoryRepository {

    @Autowired
    private ProcessHistoryMapper processHistoryMapper;

}
