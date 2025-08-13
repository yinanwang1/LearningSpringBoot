package com.yn.customer.util;

import com.yn.customer.annotation.MyAnnotation;

/**
 * Parent
 *
 * @author arthurwang
 * @version 1.0
 * 2025/7/15 18:25
 **/
@MyAnnotation(name = "Class")
class Parent {
    @MyAnnotation(name = "美丽新世界")
    public void foo() {

    }
}
