package com.github.sparkzxl.authorization.infrastructure.netty;

import com.alibaba.fastjson.JSON;
import com.github.sparkzxl.authorization.domain.model.aggregates.AuthorizeState;
import com.github.sparkzxl.core.jackson.JsonUtil;
import com.github.sparkzxl.open.service.OauthService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.HashMap;
import java.util.Map;

/**
 * description: WebSocket处理器，处理websocket连接相关
 *
 * @author: zhouxinlei
 * @date: 2021-02-25 17:16:46
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final OauthService oauthService;

    public MyWebSocketHandler(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");

        //添加到channelGroup通道组
        MyChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭！");
        //添加到channelGroup 通道组
        MyChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数 by zhengkai.blog.csdn.net
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap = getUrlParams(uri);
            System.out.println("接收到的参数是：" + JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
            }

        } else if (msg instanceof TextWebSocketFrame) {
            //正常的TEXT消息类型
            TextWebSocketFrame frame = (TextWebSocketFrame) msg;
            String text = frame.text();
            System.out.println("客户端收到服务器数据：" + text);
            AuthorizeState authorizeState = JsonUtil.parse(text, AuthorizeState.class);
            String replyMsg = "";
            if (ObjectUtils.isNotEmpty(authorizeState)) {
                OAuth2AccessToken oAuth2AccessToken = oauthService.callBack(authorizeState.getCode(), authorizeState.getState());
                replyMsg = JsonUtil.toJson(oAuth2AccessToken);
            }
            sendAllMessage(replyMsg);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }

    private void sendAllMessage(String message) {
        //收到信息后，群发给所有channel
        MyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

    private static Map getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }
}
