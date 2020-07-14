package com.sparksys.oauth.interfaces.controller;

import com.nimbusds.jose.JWSObject;
import com.sparksys.commons.core.utils.ResponseResultUtils;
import com.sparksys.commons.jwt.config.service.JwtTokenService;
import com.sparksys.commons.oauth.service.OauthService;
import com.sparksys.commons.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.util.Map;


/**
 * description：授权登录管理
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 */
@RestController
@RequestMapping("/oauth")
@ResponseResult
@Api(tags = "授权登录管理")
public class OauthController {

    private final OauthService oauthService;
    private final JwtTokenService jwtTokenService;

    public OauthController(OauthService oauthService, JwtTokenService jwtTokenService) {
        this.oauthService = oauthService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/token")
    @Trace(operationName = "oauth_get_token_trace")
    public OAuth2AccessToken getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ActiveSpan.tag("getAccessToken", "get授权登录");
        return oauthService.getAccessToken(principal, parameters);
    }

    @PostMapping("/token")
    @Trace(operationName = "oauth_post_token_trace")
    public OAuth2AccessToken postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ActiveSpan.tag("postAccessToken", "post授权登录");
        return oauthService.postAccessToken(principal, parameters);
    }

    @GetMapping("/getCurrentUser")
    @ApiOperation("获取当前用户")
    public Object getCurrentUser(HttpServletRequest httpRequest) throws ParseException {
        String token = ResponseResultUtils.getAuthHeader(httpRequest);
        JWSObject jwsObject = JWSObject.parse(token);
        return jwsObject.getPayload();
    }
}
