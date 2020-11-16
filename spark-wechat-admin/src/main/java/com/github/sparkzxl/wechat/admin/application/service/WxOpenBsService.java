package com.github.sparkzxl.wechat.admin.application.service;

import com.github.sparkzxl.wechat.admin.interfaces.dto.open.WechatAuthUrlDTO;

/**
 * description: 微信开放接口（业务实现）
 *
 * @author: zhouxinlei
 * @date: 2020-11-16 19:42:44
*/
public interface WxOpenBsService {

    /**
     * 获取预授权链接
     * @param wechatAuthUrlDTO 获取授权链接入参
     * @return String
     */
    String getPreAuthUrl(WechatAuthUrlDTO wechatAuthUrlDTO);
}
