package com.cb.common.interceptor;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.common.util.concurrent.RateLimiter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class RateLimiterInterceptor implements HandlerInterceptor {


    private  RateLimiter rateLimiter = RateLimiter.create(10);
//每秒十个请求

//    public RateLimiterInterceptor(RateLimiter rateLimiter) {
//        super();
//        this.rateLimiter = rateLimiter;
//
//    }

@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(this.rateLimiter.tryAcquire()) {
            log.info("获取令牌成功");
            /**
             * 成功获取到令牌
             */
            return true;
        }

        /**
         * 获取失败，直接响应“错误信息”
         * 也可以通过抛出异常，通过全全局异常处理器响应客户端
         */
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.getWriter().write("服务器繁忙");
        log.info("获取令牌失败");
        return false;
    }


}