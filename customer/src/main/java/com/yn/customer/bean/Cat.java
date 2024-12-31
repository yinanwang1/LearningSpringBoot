package com.yn.customer.bean;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.Cloneable;

public class Cat implements Cloneable<Cat> {
    private String name;
    private int age;

    @Override
    public Cat clone() {
        try {
            return (Cat) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneRuntimeException(e);
        }
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
