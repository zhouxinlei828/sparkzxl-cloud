package com.github.sparkzxl.authority.interfaces.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.github.sparkzxl.log.annotation.WebLog;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.github.sparkzxl.jwt.service.JwtTokenService;

import java.util.Map;


/**
 * description：密钥管理
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 */
@RestController
@WebLog
@Api(tags = "密钥管理")
@Slf4j
public class KeyPairController {

    private final JwtTokenService jwtTokenService;

    public KeyPairController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> publicKey() {
        log.info("获取公钥====");
        return new JWKSet(jwtTokenService.getRsaPublicKey()).toJSONObject();
    }

}
