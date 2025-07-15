package com.yn.customer.util;

import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.MONTH_OF_YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.DAY_OF_MONTH)
            .appendLiteral(" ")
            .appendValue(ChronoField.HOUR_OF_DAY)
            .appendLiteral(":")
            .appendValue(ChronoField.MINUTE_OF_HOUR)
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE)
            .appendLiteral(".")
            .appendValue(ChronoField.MILLI_OF_SECOND).toFormatter();

    public static void main(String[] args) {

        Child child = new Child();
        Arrays.stream(child.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .findFirst().ifPresent(method -> {
                    try {
                        method.invoke(child, "hello");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(child);

    }

    static class Parent<T> {
        AtomicInteger updateCount = new AtomicInteger();
        private T value;

        @Override
        public String toString() {
            return String.format("value: %s updateCount: %d", value, updateCount.get());
        }

        public void setValue(T value) {
            System.out.println("Parent setValue called");
            this.value = value;

            updateCount.incrementAndGet();
        }
    }

    static class Child extends Parent<String> {
        @Override
        public void setValue(String value) {
            System.out.println("Child setValue called");

            super.setValue(value);
        }
    }
}










