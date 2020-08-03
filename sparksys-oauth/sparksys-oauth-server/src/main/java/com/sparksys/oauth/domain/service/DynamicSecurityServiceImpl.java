package com.sparksys.oauth.domain.service;

import com.sparksys.oauth.domain.repository.IAuthUserRepository;
import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.oauth.infrastructure.entity.RoleResource;
import com.sparksys.oauth.service.DynamicSecurityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DynamicSecurityServiceImpl implements DynamicSecurityService {

    private final IAuthUserRepository authUserRepository;

    public DynamicSecurityServiceImpl(IAuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        List<RoleResource> roleResources = authUserRepository.getRoleResourceList();
        Map<String, ConfigAttribute> configAttributeMap = new ConcurrentHashMap<>(roleResources.size());
        for (RoleResource resource : roleResources) {
            if (StringUtils.isNotEmpty(resource.getPath())) {
                configAttributeMap.put(resource.getPath(),
                        new SecurityConfig(resource.getRoleCode()));
            }
        }
        return configAttributeMap;
    }
}
