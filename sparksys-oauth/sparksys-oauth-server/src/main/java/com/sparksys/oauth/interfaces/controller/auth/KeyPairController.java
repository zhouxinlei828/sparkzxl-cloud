package com.sparksys.oauth.interfaces.controller.auth;

import com.nimbusds.jose.jwk.JWKSet;
import com.sparksys.log.annotation.WebLog;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.sparksys.jwt.service.JwtTokenService;

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
public class KeyPairController {

    private final JwtTokenService jwtTokenService;

    public KeyPairController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> publicKey() {
        return new JWKSet(jwtTokenService.getRsaPublicKey()).toJSONObject();
    }

}
