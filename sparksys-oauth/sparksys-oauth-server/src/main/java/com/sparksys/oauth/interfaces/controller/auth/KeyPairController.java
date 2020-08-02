package com.sparksys.oauth.interfaces.controller.auth;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.sparksys.log.annotation.WebLog;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
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

    private final KeyPair keyPair;

    public KeyPairController(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> publicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

}
