package com.github.sparkzxl.test.interfaces.controller;

import com.github.sparkzxl.cache.template.CacheTemplate;
import com.github.sparkzxl.core.utils.BuildKeyUtils;
import com.github.sparkzxl.core.utils.DateUtils;
import com.github.sparkzxl.file.dto.FileDTO;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.test.application.service.IAuthUserService;
import com.github.sparkzxl.test.infrastructure.client.FileClient;
import com.github.sparkzxl.test.infrastructure.entity.AuthUser;
import com.github.sparkzxl.test.interfaces.dto.TestDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/test")
@ResponseResult
@WebLog
@Api(tags = "test管理")
public class TestController {

    private final IAuthUserService authUserService;
    private final CacheTemplate cacheTemplate;

    public TestController(IAuthUserService authUserService, CacheTemplate cacheTemplate) {
        this.authUserService = authUserService;
        this.cacheTemplate = cacheTemplate;
    }


    @GetMapping("/boolean")
    public boolean testBoolean(){
        return true;
    }


    @PostMapping("/testJsonTime")
    public TestDTO testJsonTime(@RequestBody TestDTO testDTO){
        return testDTO;
    }

    @PostMapping("/testJsonRedis")
    public AuthUser testJsonRedis(Long id){
        AuthUser authUser = authUserService.getById(id);
        String generateKey = BuildKeyUtils.generateKey("auth_user", authUser.getId());
        cacheTemplate.set(generateKey,authUser);
        return cacheTemplate.get(generateKey);
    }
}
