package com.sparksys.oauth.domain.service;

import com.sparksys.oauth.domain.repository.IAuthResourceRepository;
import com.sparksys.oauth.infrastructure.entity.AuthResource;
import com.sparksys.commons.security.service.DynamicSecurityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("dynamicSecurityService")
public class DynamicSecurityServiceImpl implements DynamicSecurityService {

    private final IAuthResourceRepository authResourceRepository;

    public DynamicSecurityServiceImpl(IAuthResourceRepository authResourceRepository) {
        this.authResourceRepository = authResourceRepository;
    }

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        List<AuthResource> authResources = authResourceRepository.authResourceList();
        Map<String, ConfigAttribute> configAttributeMap = new ConcurrentHashMap<>(authResources.size());
        for (AuthResource resource : authResources) {
            if (StringUtils.isNotEmpty(resource.getRequestUrl())){
                configAttributeMap.put(resource.getRequestUrl(),
                        new SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
        }
        return configAttributeMap;
    }
}
