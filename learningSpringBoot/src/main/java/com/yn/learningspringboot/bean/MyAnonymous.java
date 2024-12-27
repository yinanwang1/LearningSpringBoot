package com.yn.learningspringboot.bean;

import java.util.HashMap;
import java.util.Optional;

public class MyAnonymous {
    public static void main(String[] args) {
        User user = null;
        user = Optional.ofNullable(user).orElseThrow(() -> new RuntimeException("user is null"));
        System.out.println("user is " + user.toString());
    }
}
