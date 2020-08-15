package com.sparksys.oauth.application.event;

import com.sparksys.oauth.infrastructure.entity.RoleResource;
import com.sparksys.oauth.infrastructure.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: 初始化角色资源加载类
 *
 * @author: zhouxinlei
 * @date: 2020-08-02 22:10:40
 */
@Component
public class InitRolePathApplicationRunner implements CommandLineRunner {

    public static final String RESOURCE_ROLES_MAP = "auth:resource_roles_map";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public void run(String... args) throws Exception {
        List<RoleResource> roleResources = authUserMapper.getRoleResourceList();
        Map<String, Object> roleResourceMap = roleResources.stream().collect(Collectors.toMap(RoleResource::getPath, RoleResource::getRoleCode));
        redisTemplate.opsForHash().putAll(RESOURCE_ROLES_MAP, roleResourceMap);
    }
}
