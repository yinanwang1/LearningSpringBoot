package com.yn.customer.annotation;

import java.lang.annotation.*;

/**
 * @author arthurwang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Inherited
public @interface ValidateAge {
    /**
     * 最小值
     */
    int min() default 18;

    /**
     * 最大值
     */
    int max() default 99;

    /**
     * 默认值
     */
    int value() default 20;
}
