package com.sparksys.activiti.infrastructure.mapper;

import com.sparksys.activiti.infrastructure.entity.ProcessInstance;
import com.sparksys.activiti.infrastructure.entity.ProcessTaskStatus;
import com.sparksys.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 流程状态记录Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:18:25
 */
@Repository
public interface ProcessTaskStatusMapper extends SuperMapper<ProcessTaskStatus> {

    /**
     * 查询流程实例列表
     *
     * @param name 流程名称
     * @return List<ProcessInstance>
     */
    @Select("<script> SELECT "
            + " pts.process_instance_id processInstanceId, ahp.BUSINESS_KEY_ businessKey,"
            + " arm.KEY_ processKey, arm.NAME_ processName,"
            + " IFNULL( ARU.suspension_state_, 1 ) suspensionState,"
            + " pts.process_status processStatus, ahp.START_TIME_ startTime,"
            + " ahp.START_USER_ID_ originator, ahp.END_TIME_ finishTime,"
            + " ahp.DURATION_ duration"
            + " FROM process_task_status pts"
            + " LEFT JOIN act_re_model arm ON pts.model_id = arm.ID_"
            + " LEFT JOIN act_hi_procinst ahp ON ahp.PROC_INST_ID_ = pts.process_instance_id"
            + " LEFT JOIN act_ru_execution ARU ON ARU.PROC_INST_ID_ = pts.process_instance_id"
            + " AND ARU.suspension_state_ = 2 "
            + "  <if test=\"name != null\">"
            + "     where arm.NAME_ like CONCAT(#{name},'%')"
            + "  </if>"
            + "  </script>")
    List<ProcessInstance> getProcessInstanceList(@Param("name") String name);

}
