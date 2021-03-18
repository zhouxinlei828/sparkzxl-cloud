package com.github.sparkzxl.resource.interfaces.controller;

import com.github.sparkzxl.core.annotation.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseResult
public class CallBackController {

    @GetMapping("/callback")
    public String callback(String code){
        return code;
    }
}
