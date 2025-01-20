package com.yn.customer.annotation;

import java.lang.annotation.*;

/**
 * MyAnnotation
 *
 * @author arthurwang
 * @version 1.0
 * 2025/1/15 10:33
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {
    String name() default "";
}
