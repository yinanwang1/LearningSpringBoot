package com.yn.learningspringboot.controller;

import com.yn.learningspringboot.annotation.InitSex;
import com.yn.learningspringboot.annotation.ValidateAge;
import com.yn.learningspringboot.bean.Account;
import com.yn.learningspringboot.bean.Car;
import com.yn.learningspringboot.bean.Person;
import com.yn.learningspringboot.bean.User;
import com.yn.learningspringboot.schedule.AsyncTasks;
import com.yn.learningspringboot.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class HelloController {
    @GetMapping(value = {"/hello", "/"})
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

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", "value");

        return "GET-Login";
    }

    @Autowired
    private AsyncTasks asyncTasks;

    @GetMapping("/api-1")
    public String taskOne() {
        try {
            CompletableFuture<String> task1 = asyncTasks.doTaskOne("1");
            CompletableFuture<String> task2 = asyncTasks.doTaskTwo("2");
            CompletableFuture<String> task3 = asyncTasks.doTaskThree();

            CompletableFuture.allOf(task1, task2, task3).join();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            log.info("task one finally");
        }

        return "task one";
    }

    @GetMapping("/api-2")
    public String taskTwo() {
        try {
            CompletableFuture<String> task1 = asyncTasks.doTaskOne("3");
            CompletableFuture<String> task2 = asyncTasks.doTaskTwo("4");
            CompletableFuture<String> task3 = asyncTasks.doTaskThree();

            CompletableFuture.allOf(task1, task2, task3).join();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            log.info("task two finally");
        }
        

        return "task two";
    }

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public Account getById(@RequestParam("id") String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/checkUser")
    public String checkUser() {
        User user = new User();
        log.error("111 age is " + user.getAge());
        try {
            initUser(user);
            log.error("222 age is " + user.getAge());
            boolean checkResult = checkUser(user);
            printResult(checkResult);
            user.setAge(22);
            checkResult = checkUser(user);
            printResult(checkResult);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }

        return "checkUser";
    }

    private void initUser(User user) throws IllegalAccessException {
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            // 有注解则赋值
            if (field.isAnnotationPresent(InitSex.class)) {
                InitSex initSex = field.getAnnotation(InitSex.class);
                field.setAccessible(true);
                field.set(user, initSex.sex().toString());
                log.info("完成属性值的修改，修改值为：{}", initSex.sex().toString());
            }
        }
    }

    private boolean checkUser(User user) throws IllegalAccessException {
        Field[] fields = User.class.getDeclaredFields();
        boolean result = true;
        for (Field field : fields) {
            if (field.isAnnotationPresent(ValidateAge.class)) {
                ValidateAge validateAge = field.getAnnotation(ValidateAge.class);
                field.setAccessible(true);
                int age = (int) field.get(user);
                if (age < validateAge.min() || age > validateAge.max()) {
                    result = false;
                    log.error("年龄值不符合条件");
                }
            }
        }

        return result;
    }

    private void printResult(boolean checkResult) {
        if (checkResult) {
            log.error("校验通过");
        } else {
            log.error("校验未通过");
        }
    }
}
