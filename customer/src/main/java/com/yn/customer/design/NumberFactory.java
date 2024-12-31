package com.yn.customer.design;

public interface NumberFactory {

    // 创建方法
    Number parse(String number);

    static NumberFactory getInstance() {
        return impl;
    }

    static NumberFactory impl = new NumberFactoryImpl();
}
