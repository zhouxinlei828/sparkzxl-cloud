package com.github.sparkzxl.wechat.admin.domain.service;

import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.wechat.admin.application.service.WxOpenBsService;
import com.github.sparkzxl.wechat.admin.infrastructure.config.RedisProperies;
import com.github.sparkzxl.wechat.admin.infrastructure.config.WechatOpenProperties;
import com.github.sparkzxl.wechat.admin.interfaces.dto.open.WechatAuthUrlDTO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-11-16 20:22:18
*/
@Service
@Slf4j
public class WxOpenBsServiceImpl extends WxOpenServiceImpl implements WxOpenBsService {

    @Autowired
    private WechatOpenProperties wechatOpenProperties;
    @Autowired
    private RedisProperies redisProperies;
    private static JedisPool pool;
    private WxOpenMessageRouter wxOpenMessageRouter;

    @PostConstruct
    public void init() {
        WxOpenInRedisConfigStorage inRedisConfigStorage = new WxOpenInRedisConfigStorage(getJedisPool());
        inRedisConfigStorage.setComponentAppId(wechatOpenProperties.getComponentAppId());
        inRedisConfigStorage.setComponentAppSecret(wechatOpenProperties.getComponentSecret());
        inRedisConfigStorage.setComponentToken(wechatOpenProperties.getComponentToken());
        inRedisConfigStorage.setComponentAesKey(wechatOpenProperties.getComponentAesKey());
        setWxOpenConfigStorage(inRedisConfigStorage);
        wxOpenMessageRouter = new WxOpenMessageRouter(this);
        wxOpenMessageRouter.rule().handler((wxMpXmlMessage, map, wxMpService, wxSessionManager) -> {
            log.info("\n接收到 {} 公众号请求消息，内容：{}", wxMpService.getWxMpConfigStorage().getAppId(), wxMpXmlMessage);
            return null;
        }).next();
    }
    public WxOpenMessageRouter getWxOpenMessageRouter(){
        return wxOpenMessageRouter;
    }

    private JedisPool getJedisPool() {
        if (pool == null) {
            synchronized (WxOpenBsServiceImpl.class) {
                if (pool == null) {
                    pool = new JedisPool(redisProperies, redisProperies.getHost(),
                            redisProperies.getPort(), redisProperies.getConnectionTimeout(),
                            redisProperies.getSoTimeout(), redisProperies.getPassword(),
                            redisProperies.getDatabase(), redisProperies.getClientName(),
                            redisProperies.isSsl(), redisProperies.getSslSocketFactory(),
                            redisProperies.getSslParameters(), redisProperies.getHostnameVerifier());
                }
            }
        }
        return pool;
    }

    @Override
    public String getPreAuthUrl(WechatAuthUrlDTO wechatAuthUrlDTO) {
        try {
            return super.getWxOpenComponentService().getPreAuthUrl("http://www.baidu.com", wechatAuthUrlDTO.getAuthType(), null);
        } catch (WxErrorException e) {
            e.printStackTrace();
            SparkZxlExceptionAssert.businessFail("获取预授权链接失败");
        }
        return null;
    }
}
