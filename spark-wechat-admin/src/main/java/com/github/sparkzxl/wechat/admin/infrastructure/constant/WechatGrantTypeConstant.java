package com.github.sparkzxl.wechat.admin.infrastructure.constant;

/**
 * description: 微信授权账号类型静态常量类
 *
 * @author: zhouxinlei
 * @date: 2020-11-16 20:15:37
*/
public class WechatGrantTypeConstant {

    /**
     *  业务类型 - 小程序
     */
    public static final String MINI_APP = "0";

    /**
     *  业务类型 - 服务号
     */
    public static final String MP_SERVICE = "1";

    /**
     *  业务类型 - 企业号
     */
    public static final String MP_BUSINESS = "2";

    /**
     * 业务类型 - 订阅号
     */
    public static final String MP_SUB =  "3";

    /**
     * 业务类型 - 由以前的老帐号升级后的订阅号
     */
    public static final String MP_SUB_OF_UPGRADE =  "4";


    /**
     * 订阅号
     */
    public static final int WECHAT_MP_SUB = 0;

    /**
     * 代表由历史老帐号升级后的订阅号
     */
    public static final int WECHAT_MP_SUB_OF_UPGRADE = 1;

    /**
     *服务号
     */
    public static final int WECHAT_MP_SERVICE =  2;

}
