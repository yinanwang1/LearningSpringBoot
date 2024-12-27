package com.yn.learningspringboot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Log4j2
@Data
public class NettyClient implements Runnable {
//    static final String HOST = "lb-fxqnp60x-ci2eykb8rv0ja6wq.clb.ap-shanghai.tencentclb.com";
    static final String HOST = "118.25.170.238";
//    static final String HOST = "192.168.100.134";
//    static final String HOST = "192.168.100.127";
    static final int PORT = 9102;
    static final int SIZE = 256;

    private String content;
    private static ObjectMapper JSON = new ObjectMapper();
    public static Charset charset = Charset.forName("UTF-8");
    static final String cabinetNo = "57160031";

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
            for (int i = 0; i < SIZE; i++) {
                Thread.sleep(3000);
                MsgData batteryMsgData = new MsgData();
                batteryMsgData.version = 0;
                batteryMsgData.status = 0;
                batteryMsgData.type = 1003;
                String batteryContent = getBatteryContent();
                batteryMsgData.setBody(batteryContent);
                future.channel().writeAndFlush(batteryMsgData);

                Thread.sleep(3000);
                MsgData heartBeatMsgData = new MsgData();
                heartBeatMsgData.version = 0;
                heartBeatMsgData.status = 0;
                heartBeatMsgData.type = 1002;
//                heartBeatMsgData.setBody("{}");
                future.channel().writeAndFlush(heartBeatMsgData);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            group.shutdownGracefully();
            log.info("wyn end");
        }
    }

    private String getContent() {
        String type = "1001";
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
        params.put("cabinet_no", cabinetNo);
        params.put("cabinet_version", "16");
        params.put("client_version", "V2.2.0");
        params.put("request_id", UUID.randomUUID().toString());
        params.put("time", time);
        params.put("is_physical", true);
        try {
            return JSON.writeValueAsString(params);
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }

    private String getBatteryContent() {
        // battery
        HashMap<String, Object> battery = new HashMap<>();
        battery.put("number", "7231840003");
        battery.put("power", 69);
        battery.put("voltage", 46840);
        battery.put("lower_voltage", 3589);
        battery.put("upper_voltage", 3611);
        battery.put("temperature", 27);
        battery.put("electricity", 6020);
        battery.put("fault_code", 0);
        battery.put("full_power", "0");

        // slot
        HashMap<String, Object> slot = new HashMap<>();
        slot.put("slot_no", 1);
        slot.put("handler", "null");
        slot.put("exchange_type", 0);
        slot.put("exchange_battery", "null");
        slot.put("status", 1);
        slot.put("has_battery", 1);
        slot.put("door_status", 0);
        slot.put("charge_status", 0);
        slot.put("fault_code", 0);
        slot.put("battery", battery);

        // slots
        ArrayList<Map<String, Object>> slots = new ArrayList<>();
        slots.add(slot);

        // 封装内容
        String type = "1003";
        String time = String.valueOf(System.currentTimeMillis() / 1000);

        HashMap<String, Object> params = new HashMap<>();
        params.put("request_id", UUID.randomUUID().toString());
        params.put("time", time);
        params.put("cabinet_no", cabinetNo);
        params.put("version", "1004");
        params.put("type", type);
        params.put("slots", slots);

        try {
            return JSON.writeValueAsString(params);
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }
}
