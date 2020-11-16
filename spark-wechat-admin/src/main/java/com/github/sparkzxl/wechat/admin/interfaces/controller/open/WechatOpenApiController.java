package com.github.sparkzxl.wechat.admin.interfaces.controller.open;

import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.wechat.admin.application.service.WxOpenBsService;
import com.github.sparkzxl.wechat.admin.interfaces.dto.open.WechatAuthUrlDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.sparkzxl.web.annotation.ResponseResult;
import lombok.AllArgsConstructor;

/**
 * description: 微信开放平台管理
 *
 * @author: zhouxinlei
 * @date: 2020-11-16 19:41:29
 */
@Api(tags = "微信开放平台管理")
@RestController
@WebLog
@Slf4j
@ResponseResult
@RequestMapping("/wechat/open/")
@AllArgsConstructor
public class WechatOpenApiController {


    private final WxOpenBsService wxOpenBsService;

    @ApiOperation("获取预授权链接")
    @PostMapping("getPreAuthUrl")
    public String getPreAuthUrl(@RequestBody WechatAuthUrlDTO wechatAuthUrlDTO) {
        return wxOpenBsService.getPreAuthUrl(wechatAuthUrlDTO);
    }

}
