package com.yn.customer.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;

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

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        //String className = "com.yn.customer.util.Parent";
        //Class clazz = Class.forName(className);
        //Method[] methods = clazz.getMethods();
        //log.info("wyn aaa");
        //for (Method method : methods) {
        //    log.info("method.getName() => {}", method.getName());
        //    Annotation[] annotations = method.getAnnotations();
        //    for (Annotation annotation : annotations) {
        //        log.info("annotation.annotationType() => {}", annotation.annotationType());
        //        log.info("annotation.toString() => {}", annotation.toString());
        //        if (!annotation.annotationType().equals(MyAnnotation.class)) {
        //            continue;
        //        }
        //        MyAnnotation annotation1 = (MyAnnotation) annotation;
        //        String name = annotation1.name();
        //        log.info("annotation1.name() => {}", name);
        //    }
        //}
        //log.info("wyn bbb");
        //
        //log.info("wyn 111");
        //Annotation[] annotations = clazz.getAnnotations();
        //for (Annotation annotation : annotations) {
        //    log.info("annotation.annotationType() => {}", annotation.annotationType());
        //    log.info("annotation.toString() => {}", annotation.toString());
        //    MyAnnotation annotation1 = (MyAnnotation) annotation;
        //    String name = annotation1.name();
        //    log.info("annotation1.name() => {}", name);
        //}
        //
        //
        //className = "com.yn.customer.util.Child";
        //clazz = Class.forName(className);
        //
        //log.info("wyn 222");
        //annotations = clazz.getAnnotations();
        //for (Annotation annotation : annotations) {
        //    log.info("annotation.annotationType() => {}", annotation.annotationType());
        //    log.info("annotation.toString() => {}", annotation.toString());
        //    MyAnnotation annotation1 = (MyAnnotation) annotation;
        //    String name = annotation1.name();
        //    log.info("annotation1.name() => {}", name);
        //}

    }


}










