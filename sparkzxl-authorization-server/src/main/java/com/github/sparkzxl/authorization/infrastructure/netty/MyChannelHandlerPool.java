package com.github.sparkzxl.authorization.infrastructure.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * description: 通道组池，管理所有websocket连接
 *
 * @author: zhouxinlei
 * @date: 2021-02-25 17:16:21
 */
public class MyChannelHandlerPool {

    public MyChannelHandlerPool() {
    }

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
