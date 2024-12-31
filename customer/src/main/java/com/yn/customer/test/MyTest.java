package com.yn.customer.test;

import cn.hutool.log.dialect.log4j2.Log4j2Log;
import cn.hutool.log.level.Level;
import sun.misc.Unsafe;

public class MyTest {
    private static Log4j2Log log = new Log4j2Log(MyTest.class);

    public static void main(String[] args) {
        log.log(Level.INFO, "hello World");

        Unsafe unsafe = null;

    }


}
