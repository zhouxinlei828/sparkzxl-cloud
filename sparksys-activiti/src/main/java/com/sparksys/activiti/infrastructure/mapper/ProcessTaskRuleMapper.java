package com.sparksys.activiti.infrastructure.mapper;

import com.sparksys.activiti.infrastructure.entity.ProcessTaskRule;
import com.sparksys.database.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * description: 流程跳转规则Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 13:16:16
 */
@Repository
public interface ProcessTaskRuleMapper extends SuperMapper<ProcessTaskRule> {

    /**
     * 查询任务流程控制规则
     *
     * @param processDefinitionKey 流程定义key
     * @param sourceTaskDefKey     源任务定义key
     * @param actType              流程类型
     * @return ProcessTaskRule
     */
    @Select("SELECT ptu.id, ptu.task_def_key, ptu.act_type"
            + " FROM process_task_rule ptu INNER JOIN process_detail pd ON ptu.process_detail_id = pd.id"
            + " WHERE pd.process_id = #{processDefinitionKey} AND pd.task_def_key = #{sourceTaskDefKey}"
            + " AND ptu.act_type = #{actType} LIMIT 1")
    ProcessTaskRule findActRuTaskRule(@Param("processDefinitionKey") String processDefinitionKey,
                                      @Param("sourceTaskDefKey") String sourceTaskDefKey,
                                      @Param("actType") Integer actType);
}
