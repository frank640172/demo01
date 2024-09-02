package com.itheima.impl;

import com.itheima.service.UserDao;
import com.itheima.service.UserSerivce;
import org.springframework.beans.factory.InitializingBean;

public class UserServiceImpl implements UserSerivce, InitializingBean {
//    private UserDao userDao;

    UserServiceImpl(){
        System.out.println("UserServiceImpl " + " 无参数构造函数被调用");
    }

    UserServiceImpl(String str){
        System.out.println("str= " + str);
        System.out.println("UserServiceImpl"   + " 有参数构造函数被调用");
    }

    public  void init(){
        System.out.println("UserServiceImpl " + "init()被调用");
    }

//    public  void setUserdao(UserDao userDao){
//        System.out.println(userDao);
//        this.userDao = userDao;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserServiceImpl " + "afterPropertiesSet");
    }
}
