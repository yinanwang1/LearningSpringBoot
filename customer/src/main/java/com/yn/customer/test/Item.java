package com.yn.customer.test;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Item
 *
 * @author arthurwang
 * @version 1.0
 * 2025/2/28 13:55
 **/
@Data
@RequiredArgsConstructor
public class Item {
    final String name;
    int remaining = 1000;

    @ToString.Exclude
    ReentrantLock lock = new ReentrantLock();
}
