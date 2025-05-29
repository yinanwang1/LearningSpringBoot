package com.yn.customer.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class Factorial {
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        log.info("charset: {}", Charset.defaultCharset());
    }
}
