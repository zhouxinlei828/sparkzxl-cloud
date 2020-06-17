package com.sparksys.authorization.interfaces.controller;

import com.sparksys.authorization.application.service.ILoginLogService;
import com.sparksys.commons.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/17 0017
 */
@RestController
@RequestMapping("/loginLog")
@ResponseResult
@Api(value = "LoginLog", tags = "登录日志")
public class LoginLogController {

    private final ILoginLogService loginLogService;

    public LoginLogController(ILoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }


    @ApiOperation("清空日志")
    @DeleteMapping("/clear")
    public Boolean clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = null;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            clearBeforeNum = 1000;        // 清理一千条以前日志数据
        } else if (type == 6) {
            clearBeforeNum = 10000;        // 清理一万条以前日志数据
        } else if (type == 7) {
            clearBeforeNum = 30000;        // 清理三万条以前日志数据
        } else if (type == 8) {
            clearBeforeNum = 100000;    // 清理十万条以前日志数据
        } else if (type == 9) {

        } else {
            return false;
        }
        return loginLogService.clearLog(clearBeforeTime, clearBeforeNum);
    }
}
