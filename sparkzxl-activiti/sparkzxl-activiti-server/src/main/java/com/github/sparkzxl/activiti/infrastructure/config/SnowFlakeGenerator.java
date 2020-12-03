package com.github.sparkzxl.activiti.infrastructure.config;

import cn.hutool.core.util.IdUtil;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class SnowFlakeGenerator implements IdGenerator {

    @Override
    public String getNextId() {
        return IdUtil.createSnowflake(0,10).nextIdStr();
    }
}
