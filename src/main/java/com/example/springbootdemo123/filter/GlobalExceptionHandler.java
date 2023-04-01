package com.example.springbootdemo123.filter;


import com.example.springbootdemo123.exception.TokenAuthExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常处理类
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    //用户token过期
    @ExceptionHandler(value = TokenAuthExpiredException.class)
    @ResponseBody
    public String tokenExpiredExceptionHandler(){
        log.warn("用户 token 过期");
        return "用户 token 过期";
    }
}
