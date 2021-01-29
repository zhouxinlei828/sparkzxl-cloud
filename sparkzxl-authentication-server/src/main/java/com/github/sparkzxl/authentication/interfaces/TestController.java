package com.github.sparkzxl.authentication.interfaces;

import com.github.sparkzxl.core.context.BaseContextConstants;
import com.github.sparkzxl.core.utils.RequestContextHolderUtils;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        String header = RequestContextHolderUtils.getHeader(request, BaseContextConstants.BASIC_HEADER_KEY);
        return "testKeycloak";
    }
}
