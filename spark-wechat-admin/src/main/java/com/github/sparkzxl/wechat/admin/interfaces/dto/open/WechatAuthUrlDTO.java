package com.github.sparkzxl.wechat.admin.interfaces.dto.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "获取授权链接入参")
public class WechatAuthUrlDTO {

    @ApiModelProperty("授权类型 0.小程序 1.公众号")
    private String authType;

    /**
     * 微信网页授权范围
     *
     * 1、以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，
     * 并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
     *
     * 2、以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。
     * 但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，
     * 就可在授权后获取该用户的基本信息。
     */
    @ApiModelProperty("微信网页授权范围")
    private String scope;
}
