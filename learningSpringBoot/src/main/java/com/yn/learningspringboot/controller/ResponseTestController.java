package com.yn.learningspringboot.controller;

import com.yn.learningspringboot.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
public class ResponseTestController {
    @GetMapping("/test/person")
    public Person getPerson() {
        Person person = new Person();
        person.setAge(28);
        person.setBirth(new Date());
        person.setUserName("zhangsan");

        return person;
    }
}
