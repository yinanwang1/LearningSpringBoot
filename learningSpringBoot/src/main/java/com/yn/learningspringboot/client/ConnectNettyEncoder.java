package com.yn.learningspringboot.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heqingpan on 17/8/10.
 */
public class ConnectNettyEncoder extends MessageToByteEncoder<MsgData> {
    private static final Logger log = LoggerFactory.getLogger(ConnectNettyEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, MsgData msg, ByteBuf out) throws Exception {
        log.info("ConnectNettyEncoder msg is {}", msg);
        out.writeBytes(msg.toBytes());
    }
}
