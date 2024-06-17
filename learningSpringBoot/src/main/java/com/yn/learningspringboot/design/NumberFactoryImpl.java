package com.yn.learningspringboot.design;

import java.math.BigDecimal;

public class NumberFactoryImpl implements NumberFactory {
    @Override
    public Number parse(String number) {
        return new BigDecimal(number);
    }
}
