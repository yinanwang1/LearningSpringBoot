package com.yn.customer.annotation;

import java.lang.annotation.*;

/**
 * 性别赋值
 * @author arthurwang
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Inherited
public @interface InitSex {
    enum SEX_TYPE {MAN, WOMAN}

    SEX_TYPE sex() default SEX_TYPE.MAN;
}
