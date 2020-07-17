package com.sparksys.activiti.infrastructure.mapper;

import com.sparksys.activiti.infrastructure.entity.ProcessTask;
import com.sparksys.database.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 任务处理记录 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:18:58
 */
@Repository
public interface ProcessTaskMapper extends SuperMapper<ProcessTask> {

    /**
     * 根据流程实例ID查询审批历史记录
     *
     * @param processInstanceId 流程实例ID
     * @return List<ProcessTask>
     */
    @Select("select * from process_task where proc_inst_id = #{processInstanceId,jdbcType=VARCHAR}")
    List<ProcessTask> selectByProcessInstanceId(@Param("processInstanceId") String processInstanceId);

}
