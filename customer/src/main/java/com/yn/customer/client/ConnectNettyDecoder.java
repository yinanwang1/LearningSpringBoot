package com.yn.customer.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by heqingpan on 17/8/10.
 */
public class ConnectNettyDecoder extends ByteToMessageDecoder {
    private static final Logger log = LoggerFactory.getLogger(ConnectNettyDecoder.class);
    String id;

    public ConnectNettyDecoder() {
        id = UUID.randomUUID().toString();
    }

    static int MSG_HEAD_LEN = 8;
    static int HEADER_MODE  = 0;
    static int BODY_MODE    = 1;

    int     nextLen = MSG_HEAD_LEN;
    int     mode    = HEADER_MODE;
    MsgData lastMsg = null;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf inBuf, List<Object> outBuf) throws Exception {
        log.info("ConnectNettyDecoder");
        int i = 0, l = inBuf.readableBytes();
        if (l < nextLen) {
            return;
        }
        if (mode == HEADER_MODE) {
            MsgData msg = new MsgData();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream(nextLen);
            inBuf.readBytes(outStream, nextLen);
            msg.decodeHeader(outStream.toByteArray());
            l = l - 8;
            if (msg.length == 0) {
                outBuf.add(msg);

                mode = HEADER_MODE;
                lastMsg = null;
                nextLen = MSG_HEAD_LEN;
                decode(channelHandlerContext, inBuf, outBuf);
            } else {
                mode = BODY_MODE;
                lastMsg = msg;
                nextLen = msg.length;
            }
        }
        if (l < nextLen) {
            return;
        }
        if (mode == BODY_MODE) {
            assert lastMsg != null;
            ByteArrayOutputStream outStream = new ByteArrayOutputStream(nextLen);
            inBuf.readBytes(outStream, nextLen);
            lastMsg.body = outStream.toByteArray();
            outBuf.add(lastMsg);

            mode = HEADER_MODE;
            lastMsg = null;
            nextLen = MSG_HEAD_LEN;
            decode(channelHandlerContext, inBuf, outBuf);
        }
    }
}
