package com.yn.learningspringboot.controller;

import com.yn.learningspringboot.bean.Car;
import com.yn.learningspringboot.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/new_year.jpg")
    public String hello() {


        return "Hello";
    }
}
