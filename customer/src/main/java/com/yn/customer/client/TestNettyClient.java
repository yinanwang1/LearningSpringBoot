package com.yn.customer.client;

public class TestNettyClient {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient("Hello Netty ");
        new Thread(nettyClient).start();
    }
}
