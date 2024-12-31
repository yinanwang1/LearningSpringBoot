package com.yn.customer.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了");
        request.setAttribute("code", 200);
//        转发到 /success 请求
        return "forward:/success";

    }

    @GetMapping("/cars/{path}")
    public Map carssell(@MatrixVariable("low") Integer low, @MatrixVariable("brand") List<String> brand, @PathVariable String path) {
        Map<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);

        return map;
    }

    @GetMapping("/boss/{bossId}/{empId}")
    public Map<String, Object> boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);

        return map;

    }

    @GetMapping("/params")
    public String testParam(Map<String, Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        map.put("hello", "world666");
        model.addAttribute("world", "hello 666");
        request.setAttribute("message", "helloWorld");

        Cookie cookie = new Cookie("c1", "v1");
        cookie.setDomain("localhost");
        response.addCookie(cookie);

        request.setAttribute("msg", "成功了");
        request.setAttribute("code", 200);


        return "forward:/success";

    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("reqMethod", request.getAttribute("msg"));
//        map.put("msg", msg);
        map.put("hello", request.getAttribute("hello"));
        map.put("world", request.getAttribute("world"));
        map.put("message", request.getAttribute("message"));

        return map;
    }
}
