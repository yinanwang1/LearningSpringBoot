package com.yn.customer.controller;

import com.yn.customer.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class ParameterTestController {

    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id, @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("inters")List<String> inters,
                                      @RequestParam Map<String, String> params) {
        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("name", name);
//        map.put("pv", pv);
//        map.put("userAgent", userAgent);
//        map.put("headers", header);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
//        map.put("_ga", _ga);

        return map;
    }

    @PostMapping("/save")
    public Map postMethod(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);

        return map;
    }

    // 在GET 和POST 都可以使用数据绑定
    @PostMapping("/saveuser")
    public Person saveUser(Person person) {
        return person;
    }

}
