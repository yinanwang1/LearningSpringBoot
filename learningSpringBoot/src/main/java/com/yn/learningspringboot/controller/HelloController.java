package com.yn.learningspringboot.controller;

import com.yn.learningspringboot.bean.Car;
import com.yn.learningspringboot.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String handle(@RequestParam("name") String name) {
        log.info("请求进来了啊");
        return "Hello, Spring boot 2!" + " 你好：" + name;
    }
    @Autowired
    Car car;

    @Autowired
    Person person;

    @RequestMapping("/car")
    public Car car(){
        return car;
    }

    @RequestMapping("/person")
    public Person person(){
        return person;
    }

    @RequestMapping(value = "/new_year.jpg")
    public String hello() {
        return "Hello";
    }

    // 跟视频编写demo
    @GetMapping(value = "/user")
    public String getUser() {
        return "GET-张三";
    }

    @PostMapping(value = "/user")
    public String saveUser() {
        return "POST-张三";
    }

    @PutMapping(value = "/user")
    public String putUser() {
        return "PUT-张三";
    }

    @DeleteMapping(value = "/user")
    public String deleteUser() {
        return "DELETE-张三";
    }
}
