package com.yn.learningspringboot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.UUID;

@Log4j2
@Data
public class NettyClient implements Runnable {
    static final String HOST = "120.55.30.81";
    static final int PORT = 9102;
    static final int SIZE = 256;

    private String content;
    private static ObjectMapper JSON = new ObjectMapper();
    public static Charset charset = Charset.forName("UTF-8");

    public NettyClient(String content) {
        this.content = content;
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ConnectNettyDecoder());
                            pipeline.addLast(new ConnectNettyEncoder());
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
            // 过3秒后，再发送
            Thread.sleep(3000);
            MsgData msgData = new MsgData();
            msgData.version = 1;
            msgData.status = 1;
            msgData.type = 1001;
            String body = getContent();
            msgData.setBody(body);
            future.channel().writeAndFlush(msgData);
//            log.info("params is {}", msgData.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            group.shutdownGracefully();
            log.info("wyn end");
        }
    }

    private String getContent() {
        String type = "1001";
        String cabinetNo = "75211004";
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String key = "e57c76597d5e11e780cd985aeb89a1ce";
        String sign = "";
        try {
            sign = DigestUtils.md5Hex(String.format("%s%s%s%s", type, cabinetNo, time, key));
        } catch (Exception e) {
            return sign;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("sign", sign);
        params.put("cabinet_no", "75211004");
        params.put("cabinet_version", "16");
        params.put("client_version", "V2.2.0");
        params.put("request_id", UUID.randomUUID().toString());
        params.put("time", time);
        params.put("is_physical", false);
        try {
            return JSON.writeValueAsString(params);
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }
}
