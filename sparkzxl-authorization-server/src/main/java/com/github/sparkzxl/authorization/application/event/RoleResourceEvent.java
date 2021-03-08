package com.github.sparkzxl.authorization.application.event;

import com.github.sparkzxl.authorization.domain.model.aggregates.ResourceSource;
import org.springframework.context.ApplicationEvent;

/**
 * description: 角色资源事件
 *
 * @author: zhouxinlei
 * @date: 2021-03-08 14:13:18
 */
public class RoleResourceEvent extends ApplicationEvent {

    public RoleResourceEvent(ResourceSource source) {
        super(source);
    }
}
