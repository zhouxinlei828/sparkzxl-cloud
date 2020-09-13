package com.github.sparkzxl.activiti.infrastructure.mapper;

import cn.hutool.json.JSONUtil;
import com.github.sparkzxl.activiti.ActivitiApplication;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessInstance;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
@Slf4j
class ProcessTaskStatusMapperTest {

    @Autowired
    private ProcessTaskStatusMapper processTaskStatusMapper;

    @Test
    void getProcessInstanceList() {
        List<ProcessInstance> processInstances = processTaskStatusMapper.getProcessInstanceList("请假");
        log.info("processInstances = {}", JSONUtil.toJsonPrettyStr(processInstances));
    }
}
