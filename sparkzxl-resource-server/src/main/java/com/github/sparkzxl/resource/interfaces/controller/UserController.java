package com.github.sparkzxl.resource.interfaces.controller;

import com.github.sparkzxl.core.annotation.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResponseResult
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    public Authentication info(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
