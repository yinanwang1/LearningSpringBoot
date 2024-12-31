package com.yn.customer.interceptor;

import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录检查
 * 1. 配置好拦截器要拦截哪些请求
 * 2. 把这些配置放在容器中
 */

@Slf4j
@NonNullApi
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 登录检查逻辑
//        HttpSession session = request.getSession();
//        Object loginUser = session.getAttribute("loginUser");
//        if (null != loginUser) {
//            System.out.println("wyn true");
//            return true;
//        }
//
//        System.out.println("wyn false");
//
//        // 放行
//        return false;\

        return true;
    }

    /**
     * 目标方法执行完成以后
     *
     * @param request      current HTTP request
     * @param response     current HTTP response
     * @param handler      the handler (or {@link HandlerMethod}) that started asynchronous
     *                     execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     *                     (can also be {@code null})
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        log.info("wyn afterCompletion");
    }
}
