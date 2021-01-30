package com.github.sparkzxl.authentication.interfaces;

import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ResponseResult
@WebLog
@Api(tags = "授权管理")
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping("testKeycloak")
    public AuthUserInfo getAccessTokenInfo(@ApiIgnore AuthUserInfo<String> authUserInfo) {
        return authUserInfo;
    }
}
