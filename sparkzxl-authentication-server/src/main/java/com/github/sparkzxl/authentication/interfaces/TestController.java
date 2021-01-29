package com.github.sparkzxl.authentication.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.github.sparkzxl.core.utils.RequestContextHolderUtils;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@ResponseResult
@WebLog
@Api(tags = "授权管理")
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping("testKeycloak")
    public String getAccessTokenInfo(HttpServletRequest request) {
        ServletRequestAttributes attributes = RequestContextHolderUtils.getRequestAttributes();
        Principal principal = attributes.getRequest().getUserPrincipal();
        return "testKeycloak";
    }
}
