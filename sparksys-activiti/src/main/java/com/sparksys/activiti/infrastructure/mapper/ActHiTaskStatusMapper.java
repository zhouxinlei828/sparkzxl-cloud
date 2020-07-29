package com.sparksys.activiti.infrastructure.mapper;

import com.sparksys.activiti.infrastructure.entity.ActHiTaskStatus;
import com.sparksys.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 任务状态记录Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:18:25
 */
@Repository
public interface ActHiTaskStatusMapper extends SuperMapper<ActHiTaskStatus> {

}