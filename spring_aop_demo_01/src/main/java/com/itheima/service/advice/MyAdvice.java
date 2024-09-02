package com.itheima.service.advice;

public class MyAdvice {
    public  void beforeAdvice(){
        System.out.println("前置增强....");
    }

    public  void afterReturningAdvice(){
        System.out.println("后置增强....");
    }
}
