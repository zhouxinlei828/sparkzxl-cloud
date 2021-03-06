package com.github.sparkzxl.test.interfaces.controller;

import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.test.infrastructure.producer.TestProducer;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
@ResponseResult
@WebLog
@Api(tags = "mq测试管理")
public class MqController {

    private final TestProducer testProducer;

    public MqController(TestProducer testProducer) {
        this.testProducer = testProducer;
    }
    @ApiOperation("测试mq消费")
    @GetMapping("/testMq")
    public boolean testMq(@RequestParam("message") String message){
        testProducer.sendMessage(message);
        return true;
    }
}
