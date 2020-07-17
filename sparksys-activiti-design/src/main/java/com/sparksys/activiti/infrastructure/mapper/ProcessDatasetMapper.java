package com.sparksys.activiti.infrastructure.mapper;

import com.sparksys.activiti.infrastructure.entity.ActivitiTask;
import com.sparksys.activiti.infrastructure.entity.ProcessDataset;
import com.sparksys.database.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 数据集 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:18:40
 */
@Repository
public interface ProcessDatasetMapper extends SuperMapper<ProcessDataset> {

    /**
     * 根据流程实例ID查询关联的数据集基本信息
     *
     * @param processInstanceId 流程实例id
     * @return ProcessDataset
     */
    @Select("select * from process_dataset where proc_inst_id = #{processInstanceId,jdbcType=VARCHAR}")
    ProcessDataset selectByProcessInstanceId(@Param("processInstanceId") String processInstanceId);


    /**
     * 查询用户需要处理的任务
     *
     * @param assignee 用户
     * @return List<ActivitiTask>
     */
    @Select("select t1.ID_ task_id, t1.NAME_ task_name, t2.* from act_ru_task t1 "
            + "    join process_dataset t2 on t1.PROC_INST_ID_ = t2.proc_inst_id "
            + "    where t1.ASSIGNEE_ = #{assignee,jdbcType=VARCHAR}")
    List<ActivitiTask> getTasksByAssignee(@Param("assignee") String assignee);

}
