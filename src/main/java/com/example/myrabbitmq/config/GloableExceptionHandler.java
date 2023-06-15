package com.example.myrabbitmq.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloableExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Exception handleException(Exception e){
        return new Exception(e.getMessage());
    }
}
