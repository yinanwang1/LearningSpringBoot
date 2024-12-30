package com.example.customer.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private static final ConcurrentHashMap<ChannelId, ChannelHandlerContext> CLIENT_MAP = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(NettyClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CLIENT_MAP.put(ctx.channel().id(), ctx);

        log.info("ClientHandler Active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.close();

        log.info("服务端终止了服务. client port is : " + ctx.channel().localAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channelRead data. client port is {}.", ctx.channel().localAddress());
        log.info("回写数据： {}", msg);

        MsgData msgData = (MsgData) msg;
        if (2011 == msgData.type) {
            MsgData batteryMsgData = new MsgData();
            batteryMsgData.version = 0;
            batteryMsgData.status = 0;
            batteryMsgData.type = 1011;
            batteryMsgData.setBody("{}");
            ctx.channel().writeAndFlush(batteryMsgData);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("服务器发生异常[{}]", cause.getMessage());

        ctx.close();
    }

    public void channelWrite(ChannelId channelId, String msg) {
        ChannelHandlerContext ctx = CLIENT_MAP.get(channelId);
        if (ctx == null) {
            log.info("通道 [{}] 不存在", channelId);
            return;
        }

        MsgData msgData = new MsgData();
        msgData.version = 0;
        msgData.status = 0;
        msgData.type = 1002;
        msgData.length = 0;

        ctx.write(msgData);
        ctx.flush();

        log.info("channelWrite data is {}", msgData);
    }
}
